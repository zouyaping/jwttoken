package com.qtech.jwttoken.service.impl;

import com.qtech.jwttoken.entity.User;
import com.qtech.jwttoken.mapper.UserMapper;
import com.qtech.jwttoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: UserService实现类
 * @Date: 2020/1/16 16:57
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public Boolean regist(User user) {
        Integer result = userMapper.regist(user);
        return result==1;
    }
}
