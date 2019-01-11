package com.krista.springframework.v4x.ioc.test.event;

import com.krista.extend.utils.TypeUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * TestListener
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 10:58
 */
@Component
public class TestListener implements ApplicationListener<TestEvent> {
    @Override
    @Async
    public void onApplicationEvent(TestEvent event) {
        TestParam param = (TestParam) event.getSource();
        System.out.println("**** TestListener开始 ****");
        System.out.println("发送邮件:" + param.getEmail());
        System.out.println("**** TestListener结束 ****");
        TypeUtil.sleep(2000);
    }
}
