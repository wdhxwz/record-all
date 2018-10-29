package com.krista.spring.boot.admin.service;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * AdminServiceApplication
 *
 * @author dw_wangdonghong
 * @date 2018/10/29 11:04
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
public class AdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }
}
