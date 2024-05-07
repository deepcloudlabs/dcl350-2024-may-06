package com.example.hr.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardService {

	@KafkaListener(topics = "${hrEventTopic}", groupId = "security-card")
	public void listenHrEvent(String event) {
		System.err.println("New event has just arrives: %s.".formatted(event));
	}
	
}
