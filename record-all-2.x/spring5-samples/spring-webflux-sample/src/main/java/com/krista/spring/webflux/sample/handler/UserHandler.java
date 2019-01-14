package com.krista.spring.webflux.sample.handler;

import com.krista.spring.webflux.sample.model.User;
import com.krista.spring.webflux.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * UserHandler
 *
 * @author krista
 * @version V1.0
 * @since 2019/1/12 14:37
 */
@Service
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    public Flux<User> handleGetUsers() {
        return userRepository.listUsers();
    }

    public Mono<ServerResponse> handleGetUserById(ServerRequest request) {
        return userRepository.getUserById(request.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
