package com.krista.dubbo.user.center;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Provider
 *
 * @author krista
 * @version V1.0
 * @since 2019/5/24 22:54
 */
public class Provider {
    private static final Logger logger = LoggerFactory.getLogger(Provider.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        ((ClassPathXmlApplicationContext) applicationContext).start();
        logger.info("user-center-provider start...");
        try {
            System.in.read();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
