package com.qtech.jwttoken.mapper;

import com.qtech.jwttoken.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户相关操作
 * @Date: 2020/1/16 15:56
 * @Version: 1.0
 */

@Repository
public interface UserMapper {
    User login(@Param("user") User user);
    Integer regist(@Param("user") User user);
}
