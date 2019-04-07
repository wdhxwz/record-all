package com.krista.mybatis.sample.spring;

import com.krista.mybatis.sample.service.SysUserService;
import com.krista.mybatis.sample.service.SysUserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application
 *
 * @author krista
 * @version V1.0
 * @since 2019/4/6 21:22
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("mybatis-spring-application.xml");
//        SysUserMapper sysUserMapper = applicationContext.getBean(SysUserMapper.class);
//        PageHelper.startPage(1,10);
//        List<SysUser> sysUserList = sysUserMapper.selectAll();
//        System.out.println(JsonUtil.toJson(sysUserList));
        SysUserService sysUserService = applicationContext.getBean(SysUserService.class);
        sysUserService.selectAll();
    }
}
