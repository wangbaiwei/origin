package com.wbw.servcedirveruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wbw.servcedirveruser.mapper")
@EnableFeignClients
public class ServiceDirverUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDirverUserApplication.class, args);
    }

}
