package com.middlewareapplication.webclient;

import com.middlewareapplication.model.Book;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookWebClient {

    private final WebClient client = WebClient.create("http://localhost:8094");

    public Flux<Book> listOfBooks() {
        return client.get()
                .uri("/books")
                .retrieve()
                .bodyToFlux(Book.class);
    }

    public Mono<Book> getBookById(int id) {
        return client.get()
                .uri("/book/" + id)
                .retrieve()
                .bodyToMono(Book.class);
    }

    public Mono<Book> createBook(Book book) {
        return client.post()
                .uri("/book")
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class);
    }

    public Mono<Book> updateBook(Book book, int id) {
        return client.put()
                .uri("book/" + id)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class);
    }
}
