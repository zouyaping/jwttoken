package com.qtech.jwttoken.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.qtech.jwttoken.utils.JwtUtils;
import com.qtech.jwttoken.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 拦截器类
 * @Date: 2020/1/16 15:17
 * @Version: 1.0
 */

@Component
public class InterceptorConfig implements HandlerInterceptor {
    public void errorResponse(HttpServletResponse response){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.append(JSONObject.toJSONString(Result.tokenError("登录失效，请重新登录")));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // token处理
        String token = request.getHeader("token");
        if(token == null){
            errorResponse(response);
            return false;
        }else {
            JwtUtils jwtUtils = new JwtUtils();
            boolean isOk = jwtUtils.vertify(token);
            if (isOk){
                return true;
            }
            errorResponse(response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
