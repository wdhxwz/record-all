package com.krista.springframework.v4x.ioc.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * TestEvent
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/11 10:55
 */
public class TestEvent extends ApplicationEvent {
    private TestParam testParam;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TestEvent(TestParam source) {
        super(source);

        this.testParam = source;
    }
}
