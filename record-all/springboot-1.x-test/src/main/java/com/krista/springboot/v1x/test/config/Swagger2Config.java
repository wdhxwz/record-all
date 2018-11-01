package com.krista.springboot.v1x.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
                .apis(RequestHandlerSelectors.basePackage("com.krista.springboot.v1x.test.controller"))
                .paths(PathSelectors.regex("/user/.*"))
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