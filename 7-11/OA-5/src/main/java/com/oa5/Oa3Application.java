package com.oa5;

import com.oa5.util.DU;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oa3Application {

    public static void main(String[] args) {
        System.out.println(DU.getNowString());
        System.out.println(DU.getNowSortString());
        SpringApplication.run(Oa3Application.class , args);
    }

}
