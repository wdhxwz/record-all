package com.krista.microservice.simple.movie.controller;

import com.krista.microservice.simple.movie.feign.UserServiceFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * MovieController
 *
 * @author dw_wangdonghong
 * @date 2018/10/26 13:36
 */
@RestController
@RequestMapping("movie")
public class MovieController {
    private static Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @GetMapping(value = "/user/{id}")
    public Object getUser(@PathVariable Long id){
        Map map = this.restTemplate.getForObject("http://user-service/user/" + id , Map.class);

        return  map;
    }

    @GetMapping(value = "user-service")
    public List<ServiceInstance> showUserServiceInfo(){
        return this.discoveryClient.getInstances("user-service");
    }

    @GetMapping(value = "log-user-service")
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-service");
        logger.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    @GetMapping(value = "/user-feign/{id}")
    public Object getUserByFeign(@PathVariable Long id){
        return this.userServiceFeignClient.findById(id);
    }
}
