package com.example.hr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "eventPublisher", havingValue = "kafka-websocket")
public class KafkaEventListenerService {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String hrEventTopic;
	
	public KafkaEventListenerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, 
			@Value("${hrEventTopic}") String hrEventTopic) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.hrEventTopic = hrEventTopic;
	}
	
	@EventListener
	public void listenHrEvents(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(hrEventTopic, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}
	
}
