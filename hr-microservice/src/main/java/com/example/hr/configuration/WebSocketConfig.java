package com.example.hr.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@ConditionalOnProperty(name = "eventPublisher", havingValue = "kafka-websocket")
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	private final WebSocketHandler webSocketHandler;
	
	public WebSocketConfig(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/events")
		        .setAllowedOrigins("*");
	}

}
