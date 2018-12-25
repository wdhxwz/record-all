package com.krista.springframework.v4x.aop.test.annotation;

import java.lang.annotation.*;

/**
 * Tests
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/25 19:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Tests {
    Test[] value();
}
