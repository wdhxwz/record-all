package com.krista.nacos.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * NacosConfigClientApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/7 16:54
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.krista.nacos.config.servlet")
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientApplication.class, args);
    }
}
