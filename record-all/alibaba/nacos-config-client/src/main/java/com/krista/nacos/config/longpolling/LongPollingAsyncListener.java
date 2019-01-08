package com.krista.nacos.config.longpolling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * LongPollingAsyncListener 异步请求监听器
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/8 10:04
 */
public class LongPollingAsyncListener implements AsyncListener {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LongPollingAsyncListener.class);

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        LOGGER.info("onComplete...");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        AsyncContext asyncContext = event.getAsyncContext();
        asyncContext.complete();
        LOGGER.info("onTimeout...");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        LOGGER.info("onError...");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        LOGGER.info("onStartAsync...");
    }
}
