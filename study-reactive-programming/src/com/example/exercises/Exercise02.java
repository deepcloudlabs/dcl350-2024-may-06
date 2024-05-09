package com.example.exercises;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Exercise02 {

	public static void main(String[] args) {
		Job job = new Job();
		var syncResult = job.fun();
		System.out.println("Sync Result is %d.".formatted(syncResult));		
		job.gun().thenAcceptAsync( asyncResult -> {
			System.out.println("Async Result is %d.".formatted(asyncResult));
		});
		for (var i=0;i<1_000;++i) {
			try {TimeUnit.MILLISECONDS.sleep(10);}catch(Exception e) {}
			System.out.println("Application is processing data [%d]".formatted(i));
		}
	}

}

class Job {
	
	public int fun() { // sync -> blocking
		try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
		return 42;
	}
	
	public CompletableFuture<Integer> gun() { // async -> non-blocking
		return CompletableFuture.supplyAsync(() -> {
			try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
			return 42;			
		});
	}
	
	
}