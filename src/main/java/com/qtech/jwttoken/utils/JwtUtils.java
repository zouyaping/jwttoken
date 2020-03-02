package com.qtech.jwttoken.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qtech.jwttoken.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: jwt认证工具类
 * @Date: 2020/1/16 13:50
 * @Version: 1.0
 */
public class JwtUtils {
    Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    // 设置过期时间 15分钟
    private long EXPIRE_TIME = 15 * 60 * 1000;
    // 设置私钥
    private String TOKEN_SECRET = "hello";
    // 头部
    private Map<String,Object> header = new HashMap<String,Object>();
    // 签名算法实例
    private Algorithm algorithm;
    // token认证器
    private JWTVerifier verifier;

    public JwtUtils() {
        jwtInit();
    }

    public JwtUtils(long expireTime, String tokenSecret) {
        this.EXPIRE_TIME = expireTime;
        this.TOKEN_SECRET = tokenSecret;
        jwtInit();
    }
    // 签名算法和认证器初始化
    public void jwtInit(){
        this.algorithm = Algorithm.HMAC256(this.TOKEN_SECRET);
        this.verifier = JWT.require(this.algorithm).build();
        this.header.put("typ","JWT");
        this.header.put("alg","HMAC256");
    }

    /**
     * @Description： 生成token
     * 签名方法：采用 HMAC256算法，附带 user 信息生成签名
     * @param：
     * @Return: token
     */
    public String sign(User user)throws Exception{
        // 计算token过期时间
        Date date = new Date(System.currentTimeMillis() + this.EXPIRE_TIME);
        try {
            JWTCreator.Builder jwt = JWT.create().withHeader(header)  //添加头部信息
                    .withExpiresAt(date)     // 添加到期时间
                    .withClaim("username", user.getUsername())
                    .withClaim("password", user.getPassword());
            return jwt.sign(this.algorithm);
        } catch (Exception e) {
            logger.error("生成签名失败");
            e.printStackTrace();
            throw new Exception(String.format("生成签名异常",e.getMessage()));
        }
    }

    public boolean vertify(String token){
        try {
            DecodedJWT jwt = verifier.verify(token);
            logger.info("认证成功。。。。。。。。");
            return true;
        } catch (JWTVerificationException e) {
            logger.error("认证失败。。。。。。。");
            return false;
        }
    }
}
