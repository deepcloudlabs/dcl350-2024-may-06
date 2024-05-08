package com.example.hr.adapter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infra.EventPublisher;

@Service
@Adapter(EventPublisher.class)
@ConditionalOnProperty(name = "eventPublisher", havingValue = "kafka-websocket")
public class EventPublisherSpringAdapter implements EventPublisher<HrEvent> {
	private final ApplicationEventPublisher publisher;

	public EventPublisherSpringAdapter(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void publishEvent(HrEvent event) {
		publisher.publishEvent(event);
	}

}
