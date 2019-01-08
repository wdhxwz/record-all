package com.krista.nacos.config.longpolling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HandlePollingTask
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/8 10:09
 */
public class HandlePollingTask implements Runnable {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlePollingTask.class);
    private Random random = new Random();
    private AsyncContext asyncContext;
    private long sequence;
    private final AtomicLong value = new AtomicLong();

    public HandlePollingTask(long sequence, AsyncContext asyncContext) {
        this.sequence = sequence;
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        try {
            // 通过asyncContext拿到response
            PrintWriter out = asyncContext.getResponse().getWriter();
            int sleepSecends = random.nextInt(100);
            LOGGER.info("{} wait {} second", sequence, sleepSecends);
            try {
                TimeUnit.SECONDS.sleep(sleepSecends);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
            long result = value.getAndIncrement();
            out.write(Long.toString(result));
        } catch (Exception e) {
            LOGGER.error(sequence + "handle polling failed", e);
        } finally {
            // 数据写回客户端
            asyncContext.complete();
            try {
                asyncContext.getResponse().getWriter().write("finish");
            } catch (IOException e) {
            }
        }
    }
}
