package com.krista.springframework.v4x.ioc.test.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * PostProcessorConfiguration
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 9:57
 */
@Configuration
@ComponentScan
public class PostProcessorConfiguration {

    @Bean(initMethod = "init")
    public Person person() {
        Person person = new Person();
        person.setName("krista");
        person.setSex("male");

        return person;
    }
}
