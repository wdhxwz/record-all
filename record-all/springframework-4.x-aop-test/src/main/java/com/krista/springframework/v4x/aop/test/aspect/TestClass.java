package com.krista.springframework.v4x.aop.test.aspect;

import com.krista.springframework.v4x.aop.test.annotation.Test;
import com.krista.springframework.v4x.aop.test.annotation.Test2;
import org.springframework.stereotype.Service;

/**
 * TestClass
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/25 19:45
 */
@Service
public class TestClass {
    @Test2
    @Test
    @Test
    public void say() {
        System.out.println("hello");
    }
}
