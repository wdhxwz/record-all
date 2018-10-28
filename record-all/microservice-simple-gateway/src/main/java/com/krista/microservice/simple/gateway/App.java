package com.krista.microservice.simple.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
