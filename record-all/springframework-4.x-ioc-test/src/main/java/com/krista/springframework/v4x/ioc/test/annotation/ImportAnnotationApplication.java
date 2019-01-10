package com.krista.springframework.v4x.ioc.test.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;

/**
 * ImportAnnotationApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/12/7 17:31
 */
public class ImportAnnotationApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Director director = applicationContext.getBean(Director.class);
        director.say();
        System.out.println(director.getName());
        try {
            Field field = director.getClass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(director, "new Name");
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        Director director2 = applicationContext.getBean(Director.class);
        System.out.println(director2.getName());
    }
}
