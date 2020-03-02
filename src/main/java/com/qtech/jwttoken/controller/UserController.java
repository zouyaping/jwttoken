package com.qtech.jwttoken.controller;

import com.qtech.jwttoken.entity.User;
import com.qtech.jwttoken.service.UserService;
import com.qtech.jwttoken.utils.JwtUtils;
import com.qtech.jwttoken.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 用户操作控制层
 * @Date: 2020/1/16 17:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Autowired
    UserService userService;
    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody User user)throws Exception{
        User resUser = userService.login(user);
        if(user !=null && resUser !=null &&resUser.getUsername().equals(user.getUsername())){
            JwtUtils jwtUtils = new JwtUtils();
            String token = jwtUtils.sign(user);
            logger.info("登录成功。。。。。。");
            return Result.success("登录成功",token);
        }else {
            return Result.error("账户不存在或密码错误");
        }
    }
    // 注册
    @PostMapping("/regist")
    public Result regist(@RequestBody User user){
        if(user != null && userService.regist(user)){
            logger.info("用户"+user.getUsername()+"注册成功");
            return Result.successWithMsg("注册成功");
        }else {
            logger.error("用户"+user.getUsername()+"注册失败");
            return Result.error("注册失败");
        }
    }
}
