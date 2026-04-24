package com.jwwd.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.jwwd.gateway.**.mapper")
@SpringBootApplication
public class IotEdgeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotEdgeGatewayApplication.class, args);
    }
}
