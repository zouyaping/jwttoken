package com.qtech.jwttoken.interceptors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 跨域请求拦截器
 * @Date: 2020/2/1 14:55
 * @Version: 1.0
 */
@Component
public class CROSInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object host = request.getHeader("host");
        String origin = request.getHeader("Origin");
        if (origin == null){
            return true;
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        //httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");填写*号，delete等好像不可用
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS,PATCH");
        response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //if (request.getMethod().equals("OPTIONS")){
            response.setStatus(200);
        //}
        System.out.println("---------------------开始进入请求地址拦截----------------------------"+host+"==="+origin);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
