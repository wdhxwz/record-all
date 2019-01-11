package com.krista.spring.cloud.gateway.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * GatewaySampleApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 17:25
 */
@SpringBootApplication
public class GatewaySampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewaySampleApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().build();
    }
}
