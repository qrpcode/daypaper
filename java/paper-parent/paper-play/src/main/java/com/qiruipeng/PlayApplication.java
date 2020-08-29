package com.qiruipeng;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.qiruipeng.play.dao")
@EnableFeignClients
/*@EnableDubbo*/
public class PlayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayApplication.class, args);
    }
}
