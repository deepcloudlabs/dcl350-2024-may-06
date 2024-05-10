package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerReactiveQueryService {
	private final CustomerDocumentRepository customerDocumentRepository;
	
	public CustomerReactiveQueryService(CustomerDocumentRepository customerDocumentRepository) {
		this.customerDocumentRepository = customerDocumentRepository;
	}

	public Mono<CustomerDocument> findCustomerById(@NotBlank String identity) {
		return customerDocumentRepository.findById(identity);
	}

	public Flux<CustomerDocument> findCustomers(@Min(0) int pageNo, @Max(50) int pageSize) {
		return customerDocumentRepository.sayfadakiniGetir(PageRequest.of(pageNo, pageSize));
	}

}
