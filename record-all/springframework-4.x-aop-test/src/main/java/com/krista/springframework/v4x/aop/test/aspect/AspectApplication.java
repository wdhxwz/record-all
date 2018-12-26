package com.krista.springframework.v4x.aop.test.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * AspectApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/25 19:44
 */
public class AspectApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectConfiguration.class);

        TestClass testClass = applicationContext.getBean(TestClass.class);
        // testClass.say();
        testClass.say2("krista", 18);
    }
}
