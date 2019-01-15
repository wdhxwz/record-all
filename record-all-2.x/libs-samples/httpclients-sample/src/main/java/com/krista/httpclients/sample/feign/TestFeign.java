package com.krista.httpclients.sample.feign;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * TestFeign
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/15 14:20
 */
public interface TestFeign {
    /**
     * get
     */
    @RequestLine("GET /test/get?id={id}")
    Map get(@Param(value = "id") String id);

    /**
     * getUser 动态查询参数
     */
    @RequestLine("GET /test/get")
    Map getUser(@QueryMap Map<String, String> queryMap);
}
