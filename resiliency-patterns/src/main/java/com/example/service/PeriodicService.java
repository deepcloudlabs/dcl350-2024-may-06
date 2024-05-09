package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PeriodicService {
	private final BusinessService externalService;

	public PeriodicService(BusinessService externalService) {
		this.externalService = externalService;
		System.err.println(externalService.getClass());
	}

	// @Scheduled(fixedRate = 20_000)
	public void callService1() {
		System.err.println("Calling external service #1...");
		externalService.callExternalService();
	}
	
	// @Scheduled(fixedRate = 10)
	public void callService2() {
		System.err.println("Calling external service #2...");
		externalService.callAnotherService();
	}
	
	@Scheduled(fixedRate = 10_000)
	public void callService3() {
		System.err.println("Calling async external service #3...");
		externalService.callAsyncFunction().thenAcceptAsync(System.out::println);
	}
}
