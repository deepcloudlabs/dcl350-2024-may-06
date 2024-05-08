package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BinanceWebsocketClient {
	private static final String BINANCE_WS_API = "wss://stream.binance.com:9443/ws/btcusdt@trade";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var listener = new BinanceWebSocketListener();
		HttpClient.newHttpClient()
			      .newWebSocketBuilder()
				  .buildAsync(URI.create(BINANCE_WS_API), listener);
		TimeUnit.SECONDS.sleep(30);
	}

}

class BinanceWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.out.println("Connected to the binance ws server.");
		Listener.super.onOpen(webSocket);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.out.println(data.toString());
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.out.println("Disconnected from the binance ws server.");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error has occured: %s".formatted(error.getMessage()));
		Listener.super.onError(webSocket, error);
	}
	
}