package com.krista.springframework.v4x.ioc.test.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Register
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/12/7 17:48
 */
public class Register implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition definition = new RootBeanDefinition(Director.class);
        registry.registerBeanDefinition(Director.class.getSimpleName(), definition);
    }
}
