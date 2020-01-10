package com.middlewareapplication.handler;

import com.middlewareapplication.model.Book;
import com.middlewareapplication.webclient.BookWebClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class MiddlewareBookHandler {

    private BookWebClient bookWebClient = new BookWebClient();

    public Mono<ServerResponse> listBooks(ServerRequest request) {
        Flux<Book> book = bookWebClient.listOfBooks();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(book, Book.class);
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        int bookId = Integer.parseInt(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Book> bookMono = bookWebClient.getBookById(bookId);
        return bookMono.flatMap(book -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(book)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);
        return book
                .flatMap(bookWebClient::createBook)
                .flatMap(it -> ServerResponse.ok().bodyValue(it));
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);
        int bookId = Integer.parseInt(request.pathVariable("id"));
        return book
                .flatMap(it -> bookWebClient.updateBook(it, bookId))
                .flatMap(it -> ServerResponse.ok().bodyValue(it));
    }
}
