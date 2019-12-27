package com.middlewareapplication.webclient;

import com.middlewareapplication.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BookWebClient {

    private WebClient client = WebClient.create("http://localhost:8094");

    private Mono<ClientResponse> result = client.get()
            .uri("/book")
            .accept(MediaType.APPLICATION_JSON)
            .exchange();

    private Mono<Book> singleBook = client.get()
            .uri("/book/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMap(res -> res.bodyToMono(Book.class));
}
