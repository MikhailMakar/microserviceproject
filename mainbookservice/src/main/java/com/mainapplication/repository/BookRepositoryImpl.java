package com.mainapplication.repository;

import com.mainapplication.model.Book;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
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
    public Mono<Book> updateBook(Mono<Book> book, int id) {
        return book.doOnNext(value -> bookMap.put(id, value));
    }

    @Override
    public Mono<Book> saveBook(Mono<Book> book) {
        return book.flatMap(it -> Mono.justOrEmpty(bookMap.put(bookMap.size() + 1, it)));
    }

    @Override
    public Flux<Book> getAllBooks() {
        return Flux.fromStream(bookMap.values().stream());
    }
}
