package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

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
    @Retry(name = "externalservice")
    public void callAnotherService() {
    	System.err.println("Calling external service #2");
    }
    
    // @TimeLimiter(name = "callAsync",fallbackMethod = "callAsyncFunctionFallbackMethod")
    @TimeLimiter(name = "callAsync")
    @Retry(name = "externalservice",fallbackMethod = "callAsyncFunctionFallbackMethod")
    public CompletableFuture<Integer> callAsyncFunction(){
    	System.err.println("CompletableFuture<Integer> callAsyncFunction()...");
    	return CompletableFuture.supplyAsync(() -> {
    		try { TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 10));} catch (InterruptedException e) { }
    		return 42;
    	});
    } 
    
    public CompletableFuture<Integer> callAsyncFunctionFallbackMethod(Throwable t){
    	System.err.println("Running fallback strategy: %s".formatted(t.getMessage()));
    	return CompletableFuture.supplyAsync( () -> 549 );
    }
}
