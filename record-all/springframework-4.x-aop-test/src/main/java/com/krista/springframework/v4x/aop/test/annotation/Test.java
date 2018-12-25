package com.krista.springframework.v4x.aop.test.annotation;

import java.lang.annotation.*;

/**
 * Test
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/25 19:41
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Test {
}
