package com.krista.springframework.v4x.aop.test.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Invocation
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/7 12:40
 */
public class Invocation {
    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object target, Method method, Object[] params) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }
}
