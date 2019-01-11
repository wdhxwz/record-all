package com.krista.springframework.v4x.ioc.test.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * MyBeanPostProcessor
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 9:53
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = Logger.getLogger(MyBeanPostProcessor.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Person.class.getSimpleName().equalsIgnoreCase(beanName)) {
            System.out.println("******后置处理器处理bean=【" + beanName + "】开始");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Person.class.getSimpleName().equalsIgnoreCase(beanName)) {
            System.out.println("******后置处理器处理bean=【" + beanName + "】完毕!");
        }

        return bean;
    }
}
