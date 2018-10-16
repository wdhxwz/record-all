package com.krista.java8.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Auther: dw_wanghonghong
 * @Date: 2018/10/15 20:10
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("sd", "fdsf", "3e2", "ew3");
        list.forEach(
                new Consumer<String>() {
                    @Override
                    public void accept(String element) {
                        System.out.println(element);
                    }
                }
        );

        // lambda
        list.forEach(
                element -> System.out.println(element)
        );

        // 方法引用
        list.forEach(
                System.out::println
        );

        String message = "Hello lambda";
        // message = "";
        Runnable runnable = () -> {
            System.out.println(message);
            // message = "";
        };
        runnable.run();

        final String[] array = {"Hello", "world"};

        Predicate<Integer> atLeast5 = x -> x > 5;
        System.out.println(atLeast5.test(5));
    }
}
