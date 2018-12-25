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
public class TestAspect2 {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAspect2.class);

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.krista.springframework.v4x.aop.test.annotation.Tests)")
    public void annotationPointCut() {
        // 方法定义
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        LOGGER.info(method.getName() + "tests");
    }
}
