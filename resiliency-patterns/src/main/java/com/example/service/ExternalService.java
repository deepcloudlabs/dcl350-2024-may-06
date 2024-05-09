package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ExternalService implements BusinessService {

    @Retry(name = "externalservice")
	public void callExternalService() {
		if(ThreadLocalRandom.current().nextDouble()<0.8) {
			System.err.println("About to fail to calling the external service.");			
			throw new IllegalArgumentException("Something is wrong!");
		}
		System.err.println("Successfully called the external service #1.");
	}
    
    @RateLimiter(name="externalservice")
    public void callAnotherService() {
    	System.err.println("Calling external service #2");
    }
}
