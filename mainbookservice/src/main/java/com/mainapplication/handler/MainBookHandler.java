package com.mainapplication.handler;

import com.mainapplication.elasticreoisitory.BookElasticRepository;
import com.mainapplication.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class MainBookHandler {

    private final BookElasticRepository bookRepository;

    public Mono<ServerResponse> listBooks(ServerRequest request) {
        Flux<Book> book = bookRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(book, Book.class);
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        int bookId = Integer.parseInt(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Book> bookMono = bookRepository.findById(bookId);
        return bookMono.flatMap(book -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(book))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class).flatMap(book1 -> bookRepository.save(book1)
                .flatMap(book2 -> ServerResponse.ok().bodyValue(book2)));
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        return request.bodyToMono(Book.class).flatMap(book1 -> bookRepository.save(book1)
                .flatMap(book2 -> ServerResponse.ok().bodyValue(book2)));
    }
}
