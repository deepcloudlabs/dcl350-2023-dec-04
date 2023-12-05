package com.example.hr.application.business.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.example.hr.domain.TcKimlikNo;

sealed public abstract class HrEvent permits EmployeeFiredEvent, EmployeeHiredEvent{
	private final String eventId = UUID.randomUUID().toString();
	private final TcKimlikNo identity;
	private final EventType type;
	private final ZonedDateTime time = ZonedDateTime.now();

	public HrEvent(TcKimlikNo identity, EventType type) {
		this.identity = identity;
		this.type = type;
	}

	public String getEventId() {
		return eventId;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public EventType getType() {
		return type;
	}

	public ZonedDateTime getTime() {
		return time;
	}

}
