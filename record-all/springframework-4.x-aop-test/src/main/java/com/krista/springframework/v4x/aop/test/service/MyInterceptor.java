package com.krista.springframework.v4x.aop.test.service;

import java.lang.reflect.InvocationTargetException;

/**
 * MyInterceptor
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/7 12:44
 */
public class MyInterceptor implements Interceptor{
    @Override
    public void before() {
        System.out.println("before......");
    }

    @Override
    public void after() {
        System.out.println("after......");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before......");
        Object obj = invocation.proceed();
        System.out.println("around after......");
        return obj;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning......");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing......");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
