package com.krista.java.test;

import java.util.Random;

/**
 * Hello world!
 *
 * @author dw_wangdonghong
 * @date 2018/10/18 17:33
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // 生成短信验证码
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        System.out.println(verifyCode);
    }
}
