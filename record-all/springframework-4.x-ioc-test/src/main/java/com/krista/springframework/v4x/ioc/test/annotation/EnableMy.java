package com.krista.springframework.v4x.ioc.test.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableMy
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/12/7 17:53
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface EnableMy {
}
