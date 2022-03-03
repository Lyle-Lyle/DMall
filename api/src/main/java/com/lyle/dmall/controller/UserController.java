package com.lyle.dmall.controller;

import com.lyle.dmall.service.UserService;
import com.lyle.dmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(value = "提供用户的登录和注册接口",tags = "用户管理")
public class UserController {

    @Resource
    private UserService userService;


    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username", value = "用户登录账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password", value = "用户登录密码",required = true)
    })
    @GetMapping("/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam(value = "password") String pwd){
        ResultVO resultVO = userService.checkLogin(name, pwd);
        return resultVO;
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username", value = "用户注册账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password", value = "用户注册密码",required = true)
    })
    @PostMapping("/register")
    public ResultVO register(String username,String password){
        ResultVO resultVO = userService.userRegister(username, password);
        return resultVO;
    }

}
