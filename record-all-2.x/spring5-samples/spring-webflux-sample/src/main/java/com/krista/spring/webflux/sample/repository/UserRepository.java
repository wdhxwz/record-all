package com.krista.spring.webflux.sample.repository;

import com.krista.spring.webflux.sample.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * UserRepository
 *
 * @author krista
 * @version V1.0
 * @since 2019/1/12 14:37
 */
@Repository
public class UserRepository {
    private static User[] users;

    static {
        users = new User[]{
                new User("1", "krista", 21),
                new User("2", "cxx", 22)
        };
    }

    public Flux<User> listUsers() {
        return Flux.just(users);
    }

    public Mono<User> getUserById(String id) {
        Optional<User> user = Stream.of(users).filter(u -> u.getId().equals(id)).findAny();

        return Mono.just(user.get());
    }
}
