package com.oa7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableFeignClients   //开启Feign功能
public class Oa7Application {

    public static void main(String[] args) {
        log.info("OA-7 Application is starting...");
        SpringApplication.run(Oa7Application.class, args);
    }

}
