package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BinanceRestAsyncClient {
	private static final List<String> SYMBOLS = List.of("LTCBTC", "BTCUSDT", 
			"BNTETH", "ZRXBTC","EOSBTC","ZECBTC", "MTLETH", "KMDETH",
			"HSRETH", "ICNBTC");
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=%s";
	private static final AtomicInteger counter = new AtomicInteger(0);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var start = System.currentTimeMillis();
		for (var symbol : SYMBOLS) {
			var request = HttpRequest.newBuilder(URI.create(BINANCE_REST_API.formatted(symbol))).build();
			httpClient.sendAsync(request, BodyHandlers.ofString())
			          .thenAcceptAsync(response -> {
			        	  System.out.println(response.body());
			        	  if(counter.incrementAndGet() == SYMBOLS.size()) {
			        		  var stop = System.currentTimeMillis();
			        		  System.out.println("Duration: %d ms.".formatted(stop-start));			        		  
			        	  }
			          });
		}
		TimeUnit.SECONDS.sleep(3);
	}

}
