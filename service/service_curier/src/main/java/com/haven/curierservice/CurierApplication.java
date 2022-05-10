package com.haven.curierservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.haven"})
@EnableDiscoveryClient
@EnableFeignClients
public class CurierApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurierApplication.class,args);
    }

}
