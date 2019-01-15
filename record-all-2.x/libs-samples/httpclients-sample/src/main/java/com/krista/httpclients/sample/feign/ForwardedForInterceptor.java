package com.krista.httpclients.sample.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * ForwardedForInterceptor
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/15 14:46
 */
public class ForwardedForInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Forwarded-For", "origin.host.com");
    }
}
