package com.mainapplication.repository;

import com.mainapplication.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookRepository {

    Mono<Book> getBookById(int id);
    Mono<Book> updateBook(Mono<Book> book);
    Mono<Void> saveBook(Mono<Book> book);
    Flux<Book> getAllBooks();
}