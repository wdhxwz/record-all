package com.krista.spring.webflux.sample.controller;

import com.krista.spring.webflux.sample.handler.UserHandler;
import com.krista.spring.webflux.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * HelloController
 *
 * @author krista
 * @version V1.0
 * @since 2019/1/12 14:28
 */
@RestController
public class HelloController {
    @Autowired
    UserHandler userHandler;

    /**
     * spring-webmvc + servlet + Tomcat
     */
    @GetMapping("/hello")
    public String hello() {
        return "Welcome to reactive world ~";
    }

    /**
     * spring-webflux + Reactor + Netty
     */
    @GetMapping("/hello2")
    public Mono<String> hello2() {
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping("/users")
    public Flux<User> listUser() {
        return userHandler.handleGetUsers();
    }
}
