package com.oa7.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {
    
    // 通过配置属性决定使用哪个 SignService 实现
    // 在 application.yml 中设置 sign.storage.type=elasticsearch 来启用 ES 存储
    @Bean
    Logger.Level feignLoggerLevel() {
        // 设置 Feign 日志级别为 FULL
        return Logger.Level.FULL;
    }

}