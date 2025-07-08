package com.oa2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  配置全局的CORS（跨域资源共享）策略，允许所有来源、所有请求头、所有路径的POST和GET请求。具体如下：
 *  addMapping("/**")：匹配所有请求路径
 *  allowedHeaders("*")：允许所有请求头
 *  allowedOrigins("*")：允许所有来源（域名）
 *  allowedMethods("POST", "GET")：只允许 POST 和 GET 方法
 */

@Configuration
public class CrosConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("配置全局的CORS（跨域资源共享）");
        //允许所有请求
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("POST" , "GET");
    }
}
