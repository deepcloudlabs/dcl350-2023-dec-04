package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerDocumentRepository extends ReactiveMongoRepository<CustomerDocument, String>{
	Flux<CustomerDocument> findAllByAddressesCity(String city);
	Mono<CustomerDocument> findOneByEmail(String email);
	@Query("{}")
	Flux<CustomerDocument> findAll(PageRequest page);
}
