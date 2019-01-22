package com.krista.springframework.v4x.aop.test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * TestAspect
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/25 19:41
 */
@Aspect
@Component
public class TestAspect {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.krista.springframework.v4x.aop.test.annotation.Test)")
    public void annotationPointCut() {
        // 方法定义
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 返回值(需要在around中)
        Class<?> returnType = methodSignature.getReturnType();
        LOGGER.info("returnType:{}", returnType);

        // 参数名及参数值
        Object[] args = joinPoint.getArgs();
        String[] params = methodSignature.getParameterNames();
        for (int index = 0; index < args.length; index++) {
            LOGGER.info("{}:{}", params[index], args[index]);
        }

        LOGGER.info(method.getName());
        LOGGER.info(methodSignature.toShortString());
    }
}
