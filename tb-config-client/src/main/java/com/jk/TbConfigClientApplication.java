package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class TbConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TbConfigClientApplication.class, args);
    }

}
