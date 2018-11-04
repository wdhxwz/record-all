package com.krista.spring.boot.starter.swagger2;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableConfigurationProperties(Swagger2Properties.class)
@ConditionalOnClass(Api.class)
@ConditionalOnProperty(prefix = "swagger2", value = "enabled", matchIfMissing = true)
@EnableSwagger2
public class Swagger2AutoConfiguration {
    @Autowired
    private Swagger2Properties swagger2Properties;

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(swagger2Properties.getUserName(),
                swagger2Properties.getUrl(), swagger2Properties.getEmail());

        return new ApiInfoBuilder()
                .title(swagger2Properties.getApplicationName())
                .description(swagger2Properties.getDescription())
                .contact(contact)
                .version(swagger2Properties.getVersion())
                .build();
    }
}
