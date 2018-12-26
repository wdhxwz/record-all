package com.krista.java8.test.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * ExceptionTestApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/26 10:36
 */
public class ExceptionTestApplication {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 9, 7, 6, 10, 20, 0);
        integers.forEach(i -> System.out.println(50 / i));
    }
}
