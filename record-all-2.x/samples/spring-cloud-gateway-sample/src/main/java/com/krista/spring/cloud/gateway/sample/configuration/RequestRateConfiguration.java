package com.krista.spring.cloud.gateway.sample.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * RequestRateConfiguration
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/16 9:24
 */
@Configuration
public class RequestRateConfiguration {
    @Bean
    KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
