package com.krista.java.test.reflect;

/**
 * App1
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/31 10:12
 */
public class App1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.krista.java.test.reflect.ClassForName");
            System.out.println("#########分割符(上面是Class.forName的加载过程，下面是ClassLoader的加载过程)##########");
            ClassLoader.getSystemClassLoader().loadClass("com.krista.java.test.reflect.ClassForName");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int oldCapacity =15;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
    }
}
