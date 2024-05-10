package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.entity.Customer;
import com.example.crm.events.CustomerAcquiredEvent;
import com.example.crm.events.CustomerReleasedEvent;
import com.example.crm.repository.CustomerReactiveRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerReactiveRepository customerReactiveRepository;
	private final ReactiveKafkaProducerTemplate<String,String> reactiveKafkaProducerTemplate;
	private final ObjectMapper objectMapper;
	
	public CrmReactiveService(CustomerReactiveRepository customerReactiveRepository, ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate, ObjectMapper objectMapper) {
		this.customerReactiveRepository = customerReactiveRepository;
		this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
		this.objectMapper = objectMapper;
	}

	public Mono<Customer> getCustomerById(String identity) {
		return customerReactiveRepository.findById(identity);
	}

	public Flux<Customer> getCustomers(int page, int size) {
		return customerReactiveRepository.findAll(PageRequest.of(page, size));
	}

	public Mono<Customer> acquire(Customer customer) {
		return customerReactiveRepository.insert(customer)
				                         .doOnSuccess(insertedCustomer -> {
				                        	 var event = new CustomerAcquiredEvent(insertedCustomer.getIdentity());
				                        	 try {
				                        		 var eventAsJson = objectMapper.writeValueAsString(event);
				                        		 reactiveKafkaProducerTemplate.send("crm-events", null, eventAsJson).subscribe();
				                        	 }catch (Exception e) {}
				                        	 
				                         });
	}

	public Mono<Customer> release(String identity) {
		return customerReactiveRepository.findById(identity)
		                          .doOnNext(customer -> {
		                        	  customerReactiveRepository.delete(customer)
		                        	  .doOnSuccess(result -> {
		                        		  var event = new CustomerReleasedEvent(customer);
				                        	 try {
				                        		 var eventAsJson = objectMapper.writeValueAsString(event);
				                        		 reactiveKafkaProducerTemplate.send("crm-events", null, eventAsJson).subscribe();
				                        	 }catch (Exception e) {}
		                        	  }).subscribe(result -> System.out.println("Customer[%s] is released!".formatted(customer.getIdentity())));
		                          });
	}

	
}
