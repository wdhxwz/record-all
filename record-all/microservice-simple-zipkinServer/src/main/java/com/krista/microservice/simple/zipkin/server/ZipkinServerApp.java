package com.krista.microservice.simple.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Bean;
import zipkin.server.EnableZipkinServer;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

/**
 * ZipkinServerApp
 *
 * @author dw_wangdonghong
 * @date 2018/10/29 19:53
 */
@SpringBootApplication
// @EnableZipkinServer
@EnableEurekaClient
@EnableZipkinStreamServer
public class ZipkinServerApp {
    public MySQLStorage mySQLStorage(DataSource dataSource){
        return MySQLStorage.builder()
                .datasource(dataSource)
                .executor(Runnable::run)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApp.class, args);
    }
}
