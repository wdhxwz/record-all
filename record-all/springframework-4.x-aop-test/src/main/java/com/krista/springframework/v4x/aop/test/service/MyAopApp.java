package com.krista.springframework.v4x.aop.test.service;

/**
 * MyAopApp
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/7 12:57
 */
public class MyAopApp {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService)ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("wangdh");
    }
}
