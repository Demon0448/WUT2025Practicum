package com.oa7.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    
    // 通过配置属性决定使用哪个 SignService 实现
    // 在 application.yml 中设置 sign.storage.type=elasticsearch 来启用 ES 存储
} 