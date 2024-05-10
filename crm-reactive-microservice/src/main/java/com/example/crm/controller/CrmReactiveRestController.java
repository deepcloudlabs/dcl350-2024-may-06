package com.example.crm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.entity.Customer;
import com.example.crm.service.CrmReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CrmReactiveRestController {
	private final CrmReactiveService crmReactiveService;

	public CrmReactiveRestController(CrmReactiveService crmReactiveService) {
		this.crmReactiveService = crmReactiveService;
	}

	@GetMapping("{identity}")
	public Mono<Customer> findCustomerByIdentity(@PathVariable String identity) {
		return crmReactiveService.getCustomerById(identity);
	}

	@GetMapping(params = { "page", "size" })
	public Flux<Customer> findAllCustomersByPage(@RequestParam int page, @RequestParam int size) {
		return crmReactiveService.getCustomers(page, size);
	}
	
	@PostMapping
	public Mono<Customer> acquireCustomer(@RequestBody Customer customer){
		return crmReactiveService.acquire(customer);
	}
	
	@DeleteMapping("{identity}")
	public Mono<Customer> releaseCustomer(@PathVariable String identity){
		return crmReactiveService.release(identity);
	}
}
