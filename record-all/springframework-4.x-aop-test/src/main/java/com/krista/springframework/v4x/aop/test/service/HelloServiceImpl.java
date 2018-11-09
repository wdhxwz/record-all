package com.krista.springframework.v4x.aop.test.service;

/**
 * HelloServiceImpl
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/6 20:11
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("parameter name is null");
        }
        System.out.println("Hello " + name);
    }
}
