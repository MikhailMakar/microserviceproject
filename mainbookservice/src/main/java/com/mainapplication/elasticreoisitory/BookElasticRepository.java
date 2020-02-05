package com.mainapplication.elasticreoisitory;

import com.mainapplication.model.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BookElasticRepository extends ReactiveElasticsearchRepository<Book, Long> {

    @Query("{\"match\": {\"id\": \"?0\"}}")
    Mono<Book> findById(int id);
}
