package com.qtech.jwttoken.controller;

import com.qtech.jwttoken.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试接口
 * @Date: 2020/1/16 17:46
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getInfo")
    public Result test(){
        return Result.successWithMsg("验证成功、、、、、、、、、、、、、、、、");
    }
}
