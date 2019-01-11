package com.krista.springframework.v4x.ioc.test.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * MyBeanFactoryPostProcessor
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 9:55
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = Logger.getLogger(MyBeanFactoryPostProcessor.class.getName());

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("******调用了BeanFactoryPostProcessor");
        String[] beanStr = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanStr) {
            if (Person.class.getSimpleName().equalsIgnoreCase(beanName)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                MutablePropertyValues m = beanDefinition.getPropertyValues();
//                if (m.contains("name")) {
//                    m.addPropertyValue("name", "赵四");
//                    System.out.println("******修改了name属性初始值了");
//                }
                m.addPropertyValue("name", "赵四");
                System.out.println("******修改了name属性初始值了");
            }
        }
    }
}
