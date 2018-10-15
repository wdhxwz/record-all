package com.krista.java8.test.lambda;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @Auther: dw_wanghonghong
 * @Date: 2018/10/15 20:10
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        Arrays.asList("sd","fdsf","3e2","ew3").forEach(
                new Consumer<String>() {
                    @Override
                    public void accept(String element) {
                        System.out.println(element);
                    }
                }
        );

        // lambda
        Arrays.asList("sd","fdsf","3e2","ew3").forEach(
                element -> System.out.println(element)
        );

        // 方法引用
        Arrays.asList("sd","fdsf","3e2","ew3").forEach(
                System.out::println
        );
    }
}
