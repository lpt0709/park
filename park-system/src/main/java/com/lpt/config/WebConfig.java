package com.lpt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lpt.interceptor.LoginInterceptor;
import com.lpt.interceptor.UserLoginInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /*
    * 拦截器，对普通用户和管理员的部分请求进行拦截
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //管理员
        registry.addInterceptor(new LoginInterceptor())
        	.excludePathPatterns("/login")
        	.excludePathPatterns("/admin/login")
        	.excludePathPatterns("/admin/logout")
        	.addPathPatterns("/admin/*");

        //普通用户
        registry.addInterceptor(new UserLoginInterceptor())
        .addPathPatterns("/user/user-show")
        .addPathPatterns("/user/update-user")
        .addPathPatterns("/user/password")
        .addPathPatterns("/user/updatePwd")
        .addPathPatterns("/order/showOrder")
        .addPathPatterns("/message/message-save")
        .addPathPatterns("/message/delMsg")
        .addPathPatterns("/message/myMessage")
        .addPathPatterns("/car/buy");
        super.addInterceptors(registry);
    }

}
