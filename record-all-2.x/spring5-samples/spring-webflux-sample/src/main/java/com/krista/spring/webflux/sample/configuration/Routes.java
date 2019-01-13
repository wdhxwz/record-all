package com.krista.spring.webflux.sample.configuration;

import com.krista.spring.webflux.sample.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Routes
 *
 * @author krista
 * @version V1.0
 * @since 2019/1/12 14:52
 */
@Configuration
public class Routes {
    private UserHandler userHandler;

    public Routes(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

}
