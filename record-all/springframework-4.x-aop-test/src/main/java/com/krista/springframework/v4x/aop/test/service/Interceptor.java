package com.krista.springframework.v4x.aop.test.service;

import java.lang.reflect.InvocationTargetException;

/**
 * Interceptor
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/7 12:37
 */
public interface Interceptor {
    void before();
    void after();
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException ;
    void afterReturning();
    void afterThrowing();
    boolean useAround();




}
