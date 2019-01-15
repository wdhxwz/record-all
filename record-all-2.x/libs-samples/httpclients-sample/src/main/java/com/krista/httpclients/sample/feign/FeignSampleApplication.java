package com.krista.httpclients.sample.feign;

import com.krista.extend.utils.JsonUtil;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * FeignSampleApplication
 * https://blog.csdn.net/u010862794/article/details/73649616
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/15 11:46
 */
public class FeignSampleApplication {
    public static void main(String[] args) {
        TestFeign testFeign = Feign.builder()
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .requestInterceptor(new ForwardedForInterceptor())
                .target(TestFeign.class, "http://127.0.0.1:7071");
        Map data = testFeign.get("1");

        Map<String, String> queryParam = new HashMap<>(1);
        queryParam.put("id", "2");
        Map jsonResponse = testFeign.getUser(queryParam);

        System.out.println(JsonUtil.toJson(data));
        System.out.println(JsonUtil.toJson(jsonResponse));
    }
}
