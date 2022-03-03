package com.lyle.dmall.service.impl;

import com.lyle.dmall.dao.UserDao;
import com.lyle.dmall.entity.User;
import com.lyle.dmall.service.UserService;
import com.lyle.dmall.utils.MD5Utils;
import com.lyle.dmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
//    报错是因为这是聚合工程，api依赖service，所以service并不知道api里面会扫描包
//    可以@Repository加在UserDao上
    private UserDao userDao;

    @Transactional
    public ResultVO userRegister(String name, String pwd) {
        synchronized (this) {
            //1.根据用户查询，这个用户是否已经被注册
            User user = userDao.queryUserByName(name);

            //2.如果没有被注册则进行保存操作
            if (user == null) {
                //对密码加密
                String md5Pwd = MD5Utils.md5(pwd);
                user = new User();
                user.setUsername(name);
                user.setPassword(md5Pwd);
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                int i = userDao.insertUser(user);
                if (i > 0) {
                    return new ResultVO(10000, "注册成功！", null);
                } else {
                    return new ResultVO(10002, "注册失败！", null);
                }
            } else {
                return new ResultVO(10001, "用户名已经被注册！", null);
            }
        }
    }

    @Override
    public ResultVO checkLogin(String name, String pwd) {
        User user = userDao.queryUserByName(name);
        if(user == null){
            return new ResultVO(10001,"登录失败，用户名不存在！",null);
        }else{
            //比较和判断加密后的密码
            String md5Pwd = MD5Utils.md5(pwd);
            if(md5Pwd.equals(user.getPassword())){
                return new ResultVO(10000,"登录成功！",user);
            }else{
                return new ResultVO(10001,"登录失败，密码错误！",null);
            }
        }
    }
}
