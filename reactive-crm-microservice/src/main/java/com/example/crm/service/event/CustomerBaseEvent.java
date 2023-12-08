package com.example.crm.service.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public abstract class CustomerBaseEvent {
	private final String eventId = UUID.randomUUID().toString();
	private final String identity;
	private final EventType type;
	private final ZonedDateTime time = ZonedDateTime.now();

	public CustomerBaseEvent(String identity, EventType type) {
		this.identity = identity;
		this.type = type;
	}

}
