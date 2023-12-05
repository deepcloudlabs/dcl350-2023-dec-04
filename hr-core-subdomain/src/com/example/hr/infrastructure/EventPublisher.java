package com.example.hr.infrastructure;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;

@Port(PortType.DRIVEN)
public interface EventPublisher<Event> {
	public void publish(Event event);
}
