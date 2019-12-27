package com.middlewareapplication.webclient;

import com.middlewareapplication.model.Book;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookWebClient {

    private final WebClient client = WebClient.create("http://localhost:8094");

    public Flux<Book> listOfBooks() {
        return client.get()
                .uri("/book")
                .retrieve()
                .bodyToFlux(Book.class);
    }

    public Mono<Book> getBookById(int id) {
        return client.get()
                .uri("/book/" + id)
                .retrieve()
                .bodyToMono(Book.class);
    }

    public void createBook(Mono<Book> bookMono) {
        client.post()
                .uri("/book/create")
                .body(BodyInserters.fromValue(bookMono));
    }

    public Mono<Book> updateBook(Mono<Book> bookMono, int id) {
        return client.put()
                .uri("book/" + id + "/update")
                .retrieve()
                .bodyToMono(Book.class);
    }

}
