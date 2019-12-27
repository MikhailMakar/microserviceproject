package com.mainapplication.configuration;

import com.mainapplication.handler.MainBookHandler;
import com.mainapplication.repository.BookRepository;
import com.mainapplication.repository.BookRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MainBookRouter {

    @Bean
    public RouterFunction<ServerResponse> route() {
        BookRepository repository = new BookRepositoryImpl();
        MainBookHandler mainBookHandler = new MainBookHandler(repository);
        return RouterFunctions
                .route(GET("/book/{id}").and(accept(APPLICATION_JSON)), mainBookHandler::getBook)
                .andRoute(GET("/book").and(accept(APPLICATION_JSON)), mainBookHandler::listBooks)
                .andRoute(POST("/book/create").and(contentType(APPLICATION_JSON)), mainBookHandler::createBook)
                .andRoute(PUT("book/update").and(contentType(APPLICATION_JSON)), mainBookHandler::updateBook);
    }
}
