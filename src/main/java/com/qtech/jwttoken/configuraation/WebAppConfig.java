package com.qtech.jwttoken.configuraation;

import com.qtech.jwttoken.interceptors.CROSInterceptor;
import com.qtech.jwttoken.interceptors.InterceptorConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: Web配置类，添加拦截器
 * @Date: 2020/1/16 15:13
 * @Version: 1.0
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CROSInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/regist","/visitInfo/saveData");
    }
}
