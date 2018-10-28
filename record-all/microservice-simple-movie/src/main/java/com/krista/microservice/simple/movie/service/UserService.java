package com.krista.microservice.simple.movie.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserFallback")
    public Object getUser(Long id){
        return this.restTemplate.getForObject("http://user-service/user/" + id , Map.class);
    }

    public String getUserFallback(Long id){
        return "error:" + id;
    }
}
