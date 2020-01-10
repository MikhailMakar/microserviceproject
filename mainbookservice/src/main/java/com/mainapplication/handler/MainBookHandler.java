package com.mainapplication.handler;

import com.mainapplication.model.Book;
import com.mainapplication.repository.BookRepository;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class MainBookHandler {

    private final BookRepository bookRepository;

    public MainBookHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<ServerResponse> listBooks(ServerRequest request) {
        Flux<Book> book = bookRepository.getAllBooks();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(book, Book.class);
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        int bookId = Integer.parseInt(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Book> bookMono = bookRepository.getBookById(bookId);
        return bookMono.flatMap(book -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(book))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);
        return bookRepository.saveBook(book).flatMap(it -> ServerResponse.ok().bodyValue(it));
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);
        int bookId = Integer.parseInt(request.pathVariable("id"));
        return bookRepository.updateBook(book, bookId).flatMap(it -> ServerResponse.ok().bodyValue(it));
    }
}
