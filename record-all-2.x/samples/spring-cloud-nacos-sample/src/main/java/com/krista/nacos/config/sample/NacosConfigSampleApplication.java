package com.krista.nacos.config.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * NacosConfigSampleApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/16 10:53
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigSampleApplication.class, args);
    }
}
