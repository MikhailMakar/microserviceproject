package com.mainapplication.configuration;

import com.mainapplication.handler.MainBookHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class MainBookRouter {

    private final MainBookHandler mainBookHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(GET("/book/{id}").and(accept(APPLICATION_JSON)), mainBookHandler::getBook)
                .andRoute(GET("/books").and(accept(APPLICATION_JSON)), mainBookHandler::listBooks)
                .andRoute(POST("/book").and(contentType(APPLICATION_JSON)), mainBookHandler::createBook)
                .andRoute(PUT("/book").and(contentType(APPLICATION_JSON)), mainBookHandler::updateBook);
    }
}
