package com.krista.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * RedisApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/28 15:47
 */
@SpringBootApplication
public class RedisApplication {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisApplication.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(RedisApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        LOGGER.info("Sending message...");
        template.convertAndSend("chat", "Hello from Redis!");
        template.convertAndSend("chat", "Hello from Krista!");
        latch.await();

        System.exit(0);
    }
}
