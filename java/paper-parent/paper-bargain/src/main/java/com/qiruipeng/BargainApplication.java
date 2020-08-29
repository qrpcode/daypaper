package com.qiruipeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.qiruipeng.bargain.dao")
public class BargainApplication {
    public static void main(String[] args) {
        SpringApplication.run(BargainApplication.class, args);
    }
}
