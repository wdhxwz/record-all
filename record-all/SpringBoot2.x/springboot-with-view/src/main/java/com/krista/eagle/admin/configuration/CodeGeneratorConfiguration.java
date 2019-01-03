package com.krista.eagle.admin.configuration;

import com.krista.code.generator.configuration.CodeGeneratorProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CodeGeneratorConfiguration
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/23 11:38
 */
@Configuration
public class CodeGeneratorConfiguration {
    @Bean
    public CodeGeneratorProperty codeGeneratorProperty() {
        return new CodeGeneratorProperty();
    }
}
