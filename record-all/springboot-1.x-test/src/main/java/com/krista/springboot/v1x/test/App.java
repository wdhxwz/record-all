package com.krista.springboot.v1x.test;

import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.guice.EurekaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            DiscoveryManager.getInstance().shutdownComponent();
            logger.info("jvm is stopping...");
        }));
        SpringApplication.run(App.class, args);
    }
}
