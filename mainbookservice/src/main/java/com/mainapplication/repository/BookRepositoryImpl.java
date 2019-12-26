package com.mainapplication.repository;

import com.mainapplication.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepositoryImpl implements BookRepository {

    Map<Integer, Book> bookMap = new ConcurrentHashMap<>();

    public BookRepositoryImpl() {
        bookMap.put(1, new Book(1L, "Spring"));
        bookMap.put(2, new Book(2L, "Java"));
        bookMap.put(3, new Book(3L, "Effective Java"));
    }

    @Override
    public Mono<Book> getBookById(int id) {
        return Mono.justOrEmpty(bookMap.get(id));
    }

    @Override
    public Mono<Book> updateBook(Mono<Book> book) {
        return null;
    }

    @Override
    public Mono<Void> saveBook(Mono<Book> book) {
        Mono<Book> bookMono = book.doOnNext(value -> {
            bookMap.put((bookMap.keySet().size() + 1), value);
        });
        return bookMono.then();
    }

    @Override
    public Flux<Book> getAllBooks() {
        return Flux.fromStream(bookMap.values().stream());
    }
}
