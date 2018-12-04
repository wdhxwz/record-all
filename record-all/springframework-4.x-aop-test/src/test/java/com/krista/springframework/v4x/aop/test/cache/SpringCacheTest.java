package com.krista.springframework.v4x.aop.test.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * SpringCacheTest
 *
 * https://blog.csdn.net/m0_37962779/article/details/78671468
 * https://blog.csdn.net/m0_37962779/article/details/78692015
 * https://blog.csdn.net/m0_37962779/article/details/78747619
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/11/29 10:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringCacheConfig.class)
public class SpringCacheTest {
    private Logger logger = LoggerFactory.getLogger(SpringCacheTest.class);
    @Autowired
    UserService userService;
    @Test
    public void test() throws IOException {
        logger.info("invoke userService.clearCache()");
        // userService.clearCache();

        logger.info("invoke userService.findById(1L)");
        User user = userService.findById(1L);
        //从缓存读数据
        Assert.assertNotNull(user);

        logger.info("invoke userService.findById(1L)");
        User user2 = userService.findById(1L);
        Assert.assertEquals(user.getEmail(), user2.getEmail());
        User uu = new User(user.getId(), user.getName(), user.getEmail());
        uu.setEmail("new_email_addr");

        logger.info("invoke userService.save(uu)");
        userService.save(uu);

        logger.info("invoke userService.findById(1L)");
        User user3 = userService.findById(1L);
        Assert.assertEquals(uu.getEmail(), user3.getEmail());
        Assert.assertNotEquals(user2.getEmail(), user3.getEmail());
    }

    @Test
    public void test2() throws Exception{
        logger.info("invoke userService.clearCache()");
        userService.clearCache();

        logger.info("invoke userService.findByUsernameAndEmail(\"wangd\", \"wd@123.com\")");
        User user = userService.findByUsernameAndEmail("wangd", "wd@123.com");

        logger.info("invoke userService.findByUsernameAndEmail(\"wangd\", \"wd@123.com\")");
        User user2 = userService.findByUsernameAndEmail("wangd", "wd@123.com");
        Assert.assertEquals(user.getId(), user2.getId());

        logger.info("invoke userService.findByUsernameAndEmail(\"lm\", \"lm@123.com\")");
        User user3 = userService.findByUsernameAndEmail("lm", "lm@123.com");

        logger.info("invoke userService.findByUsernameAndEmail(\"lm\", \"lm@123.com\")");
        User user4 = userService.findByUsernameAndEmail("lm", "lm@123.com");
        Assert.assertNotEquals(user3.getId(), user4.getId());
    }

    @Test
    public void test3() throws Exception{
        logger.info("invoke userService.clearCache()");
        userService.clearCache();

        logger.info("invoke userService.findByUsername(\"wangd123\")");
        User user = userService.findByUsername("wangd123");

        logger.info("invoke userService.findByUsername(\"wangd123\")");
        User user2 = userService.findByUsername(user.getName());

        logger.info("invoke userService.clearCache()");
        userService.clearCache();

        logger.info("invoke userService.findByUsername(\"wangd123\")");
        User user3 = userService.findByUsername(user.getName());
        Assert.assertEquals(user.getId(), user2.getId());
        Assert.assertNotEquals(user.getId(), user3.getId());
    }
}
