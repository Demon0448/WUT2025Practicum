package com.oa7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Oa7Application {

    public static void main(String[] args) {
        log.info("OA-7 Application is starting...");
        SpringApplication.run(Oa7Application.class, args);
    }

}
