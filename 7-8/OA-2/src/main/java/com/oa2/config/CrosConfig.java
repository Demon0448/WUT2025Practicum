package com.oa2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CrosConfig implements WebMvcConfigurer{

    /**
     * 这段代码用于配置全局的跨域请求（CORS）策略，具体功能如下：
     *   允许所有来源（allowedOrigins("*")）访问后端接口。
     *   允许所有请求头（allowedHeaders("*")）在跨域请求中使用。
     *  仅允许 POST 和 GET 方法进行跨域请求（allowedMethods("POST", "GET")）。
     *  配置对所有路径（addMapping("/**")）生效。
     * @param registry
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("配置全局的跨域请求（CORS）策略");
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("POST","GET");
    }
}
