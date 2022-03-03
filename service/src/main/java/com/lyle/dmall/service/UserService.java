package com.lyle.dmall.service;

import com.lyle.dmall.vo.ResultVO;

public interface UserService {

    public ResultVO userRegister(String name, String pwd);

    public ResultVO checkLogin(String name, String pwd);
}
