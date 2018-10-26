package com.krista.microservice.simple.movie.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UserServiceFeignClient
 *
 * @author dw_wangdonghong
 * @date 2018/10/26 14:10
 */
@FeignClient(value = "user-service")
public interface UserServiceFeignClient {
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    Object findById(@PathVariable("id") Long id);
}
