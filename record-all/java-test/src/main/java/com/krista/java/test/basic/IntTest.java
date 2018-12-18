package com.krista.java.test.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * IntTest
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/18 10:06
 */
public class IntTest {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(-Integer.MIN_VALUE);
        Integer.parseInt("123");

        Map<String, Integer> map2 = new HashMap<>(4);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println(-95 % 12);
    }
}
