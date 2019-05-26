package com.krista.dubbo.consumer;

import com.krista.dubbo.user.center.api.UserService;
import com.krista.dubbo.user.center.api.bean.UserVo;
import com.krista.extend.utils.JsonUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Application
 *
 * @author krista
 * @version V1.0
 * @since 2019/5/26 7:14
 */
public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        context.start();

        UserService userService = context.getBean(UserService.class);

        UserVo userVo = userService.getUser(0L);
        System.out.println(JsonUtil.toJson(userVo));
        try {
            System.in.read();
        } catch (IOException e) {

        }
    }
}
