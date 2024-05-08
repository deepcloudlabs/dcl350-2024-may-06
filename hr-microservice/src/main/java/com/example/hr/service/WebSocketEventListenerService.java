package com.example.hr.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "eventPublisher", havingValue = "kafka-websocket")
public class WebSocketEventListenerService implements WebSocketHandler {
	private final Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;
	
	
	public WebSocketEventListenerService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void listenHrEvents(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			var message = new TextMessage(eventAsJson);
			sessions.forEach((sessionId,session) -> {
				try {
					session.sendMessage(message);
				} catch (IOException e) {
					System.err.println("Error while sending message: %s".formatted(e.getMessage()));
				}
			});
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		var sessionId = session.getId();
		System.out.println("New websocket session is open: %s".formatted(sessionId));
		sessions.put(sessionId, session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// no message is expected!
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error has occured at session (%s): %s".formatted(session.getId(),e.getMessage()));	
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		var sessionId = session.getId();
		System.out.println("New websocket session is closed: %s".formatted(sessionId));
		sessions.remove(sessionId);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}
