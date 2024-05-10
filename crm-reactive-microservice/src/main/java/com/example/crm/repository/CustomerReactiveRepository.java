package com.example.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.entity.Customer;

import reactor.core.publisher.Flux;

public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String>{
	  Flux<Customer> getAllByAddressesCity(String city);
	  @Query("{}")
	  Flux<Customer> findAll(Pageable page);
}
