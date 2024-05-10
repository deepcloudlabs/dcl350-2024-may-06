package com.example.crm.events;

import java.util.UUID;

public abstract class CrmBaseEvent {
	private final String eventId = UUID.randomUUID().toString();
	private final CrmEventType eventType;
	private final String identity;

	public CrmBaseEvent(CrmEventType eventType, String identity) {
		this.eventType = eventType;
		this.identity = identity;
	}

	public String getEventId() {
		return eventId;
	}

	public CrmEventType getEventType() {
		return eventType;
	}

	public String getIdentity() {
		return identity;
	}

}
