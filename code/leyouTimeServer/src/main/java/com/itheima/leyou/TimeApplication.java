package com.itheima.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeApplication.class, args);
    }
}
