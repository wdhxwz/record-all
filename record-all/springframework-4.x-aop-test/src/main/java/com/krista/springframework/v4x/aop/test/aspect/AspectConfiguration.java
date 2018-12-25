package com.krista.springframework.v4x.aop.test.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AspectConfiguration
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/25 19:45
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfiguration {

}
