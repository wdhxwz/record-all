package com.krista.springboot.v1x.test.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Swagger2Config
 *
 * @author dw_wangdonghong
 * @date 2018/11/1 11:30
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${info.application.name}")
    private String applicationName;
    @Value("${info.application.desc}")
    private String description;
    @Value("${info.application.version}")
    private String version;
    @Value("${info.author.name}")
    private String userName;
    @Value("${info.author.url}")
    private String url;
    @Value("${info.author.email}")
    private String email;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //.paths(PathSelectors.regex("/user/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(userName,url,email);

        return new ApiInfoBuilder()
                .title(applicationName)
                .description(description)
                //.termsOfServiceUrl("http://127.0.0.1:8080/")
                .contact(contact)
                .version(version)
                .build();
    }

}