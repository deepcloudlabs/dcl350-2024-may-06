package com.example.hr.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class HrEvent {
	private static AtomicLong sequenceCounter = new AtomicLong();
	private final String eventId = UUID.randomUUID().toString();
    private final long sequence = sequenceCounter.incrementAndGet();
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    private final TcKimlikNo identity;
    
	public HrEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public String getEventId() {
		return eventId;
	}

	public long getSequence() {
		return sequence;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}
	
}
