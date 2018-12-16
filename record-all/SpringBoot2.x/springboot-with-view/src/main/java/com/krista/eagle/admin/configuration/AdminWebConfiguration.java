package com.krista.eagle.admin.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * AdminWebConfiguration
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/15 10:15
 */
@Configuration
public class AdminWebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置首页
//        registry.addViewController("/").setViewName("forward:/index.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
