package com.middlewareapplication.configuration;

import com.middlewareapplication.handler.MiddlewareBookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MiddlewareBookRouter {

    @Bean
    public RouterFunction<ServerResponse> route() {
        MiddlewareBookHandler middlewareBookHandler = new MiddlewareBookHandler();
        return RouterFunctions
                .route(GET("/book/{id}").and(accept(APPLICATION_JSON)), middlewareBookHandler::getBook)
                .andRoute(GET("/book").and(accept(APPLICATION_JSON)), middlewareBookHandler::listBooks)
                .andRoute(POST("/book/create").and(contentType(APPLICATION_JSON)), middlewareBookHandler::createBook)
                .andRoute(PUT("book/{id}/update").and(contentType(APPLICATION_JSON)), middlewareBookHandler::updateBook);
    }
}
