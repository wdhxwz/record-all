package com.krista.java8.test.functionalInterface;

/**
 * @Auther: dw_wanghonghong
 * @Date: 2018/10/15 20:16
 * @Description:
 */
@FunctionalInterface
public interface Functional {
    void method();

    default void defaultMethod(){
        System.out.println("I am defaultMethod");
    }
}
