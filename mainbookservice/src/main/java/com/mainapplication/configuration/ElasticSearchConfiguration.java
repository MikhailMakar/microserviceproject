package com.mainapplication.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@EnableReactiveElasticsearchRepositories
public class ElasticSearchConfiguration extends AbstractReactiveElasticsearchConfiguration {

    private final String elasticHostName;

    private final String elasticPort;

    public ElasticSearchConfiguration(@Value("${elasticsearch.hostname}") String elasticHostName,
                                      @Value("${elasticsearch.port}") String elasticPort) {
        this.elasticHostName = elasticHostName;
        this.elasticPort = elasticPort;
    }

    @Override
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        return ReactiveRestClients.create(ClientConfiguration.create(String.format("%s:%s", elasticHostName, elasticPort)));
    }
}
