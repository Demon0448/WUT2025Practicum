package com.oa2.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class ServiceConfig {
    
    // 通过配置属性决定使用哪个 SignService 实现
    // 在 application.yml 中设置 sign.storage.type=elasticsearch 来启用 ES 存储
    @Bean
    Logger.Level feignLoggerLevel() {
        // 设置 Feign 日志级别为 FULL
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                ServletRequestAttributes attributes =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    String cookie = request.getHeader("Cookie");
                    if (cookie != null) {
                        template.header("Cookie", cookie);
                    }
                }
            }
        };
    }


} 