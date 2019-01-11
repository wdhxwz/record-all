package com.krista.springframework.v4x.ioc.test.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Person
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 9:56
 */
public class Person implements InitializingBean,DisposableBean,BeanNameAware,BeanFactoryAware {
    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void init(){
        System.out.println("******person init");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("******调用了BeanFactoryAware的setBeanFactory方法了");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("******调用了BeanNameAware的setBeanName方法了");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("******调用了DisposableBean的destroy方法了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("******调用了Initailization的afterPropertiesSet方法了");
    }
}
