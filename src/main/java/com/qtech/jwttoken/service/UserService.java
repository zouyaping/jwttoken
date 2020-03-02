package com.qtech.jwttoken.service;

import com.qtech.jwttoken.entity.User;

/**
 * @Description: 用户操作服务层
 * @Date: 2020/1/16 16:55
 * @Version: 1.0
 */
public interface UserService {
    User login(User user);
    Boolean regist(User user);
}
