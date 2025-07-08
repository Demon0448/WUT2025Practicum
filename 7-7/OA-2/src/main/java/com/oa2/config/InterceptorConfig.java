package com.oa2.config;

import com.oa2.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 对大部分请求进行登录验证，但排除了
     *    首页 /、登录接口 /emp/emplogin
     *    静态资源路径 /static/**
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("添加登录验证拦截器");
        registry.addInterceptor(loginInterceptor())
                .excludePathPatterns("/")
                .excludePathPatterns("/emp/emplogin")
                .excludePathPatterns("/static/**");
    }

    //需要告知系统，加载的静态文件。前后端分离后不需要使用！
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
