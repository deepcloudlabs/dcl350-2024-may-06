package com.example.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import jakarta.annotation.PostConstruct;

@Service
public class HrWebSocketClientService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	
	public HrWebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connectToServer() {
		webSocketClient.execute(this, "ws://localhost:8100/hr/api/v1/events")
		                .thenAcceptAsync(session -> System.out.println("Connected to the ws server: %s".formatted(session.getId())));
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println(message.getPayload());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
