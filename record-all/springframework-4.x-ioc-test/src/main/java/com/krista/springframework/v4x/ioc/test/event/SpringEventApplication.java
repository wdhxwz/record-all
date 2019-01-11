package com.krista.springframework.v4x.ioc.test.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * SpringEventApplication
 * <p>
 * 开启@EnableAsync
 * 在listener的onApplicationEvent上标注@Async开启事件异步处理
 * </p>
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 11:02
 */
public class SpringEventApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringEventConfiguration.class);

        // 同步事件
        TestParam param = new TestParam();
        param.setEmail("888.com");
        applicationContext.publishEvent(new TestEvent(param));
        System.out.println("aaa");
    }
}
