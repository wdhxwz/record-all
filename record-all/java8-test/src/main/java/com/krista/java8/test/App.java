package com.krista.java8.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 * @author dw_wangdonghong
 */
public class App {

    /**
     * @param args
     * @author dw_wangdonghong
     * @date 2018/10/17 18:03
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.remove("123");
        String arg = list.get(1);
        System.out.println(arg);

        List<Integer> iList = new ArrayList<>();
        iList.add(123);

        for (Integer i : iList) {
            System.out.println(i);
        }

        Integer a = 10;
        int b = 2;
        int c = a + b;
        System.out.println(c);

        Integer d = a + b;
        System.out.println(d);

        print("hello ", "world");
        print(100, "hello ", "world");
    }

    /**
     * test
     *
     * @param a
     * @param b
     * @author dw_wangdonghong
     * @date 2018/10/17 18:03
     */
    public void test(Integer a, Integer b) {


    }

    private static void print(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    private static void print(Integer age, String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(age);
    }

    /**
     * name
     */
    private String name;
}
