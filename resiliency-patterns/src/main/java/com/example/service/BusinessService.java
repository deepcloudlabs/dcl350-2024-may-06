package com.example.service;

import java.util.concurrent.CompletableFuture;

public interface BusinessService {
	public void callExternalService();

	public void callAnotherService();

	public CompletableFuture<Integer> callAsyncFunction();
}
