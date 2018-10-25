package com.krista.springboot.v1x.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot App
 * 
 * @author  dw_wangdonghong
 * @date  2018/10/18 9:29
 */
@SpringBootApplication
@EnableEurekaClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
