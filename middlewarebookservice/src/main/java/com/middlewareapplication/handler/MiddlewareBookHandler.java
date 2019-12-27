package com.middlewareapplication.handler;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class MiddlewareBookHandler {

    public Mono<ServerResponse> listBooks(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        return null;
    }
}
