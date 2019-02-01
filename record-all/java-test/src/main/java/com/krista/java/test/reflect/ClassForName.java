package com.krista.java.test.reflect;

/**
 * ClassForName
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/31 10:13
 */
public class ClassForName {
    /**
     * 静态代码块
     */
    static {
        System.out.println("执行了静态代码块");
    }

    /**
     * 静态变量
     */
    private static String staticFiled = staticMethod();

    /**
     * 赋值静态变量的静态方法
     */
    public static String staticMethod() {
        System.out.println("执行了静态方法");
        return "给静态字段赋值了";
    }
}
