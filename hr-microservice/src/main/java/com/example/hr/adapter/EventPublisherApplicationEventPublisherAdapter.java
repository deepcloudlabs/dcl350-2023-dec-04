package com.example.hr.adapter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.application.business.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;

@Adapter(port=EventPublisher.class)
@Service
@ConditionalOnProperty(name = "messaging",havingValue = "spring")
public class EventPublisherApplicationEventPublisherAdapter implements EventPublisher<HrEvent> {
	private final ApplicationEventPublisher eventPublisher;
	
	public EventPublisherApplicationEventPublisherAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publish(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
