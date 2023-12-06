package com.example.hr.adapter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.hr.application.business.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "messaging",havingValue = "rabbit")
public class EventPublisherRabbitAdapter implements EventPublisher<HrEvent> {
	private final RabbitTemplate rabbitTemplate;
	private final String exchangeName;
	private final ObjectMapper objectMapper;
	
	public EventPublisherRabbitAdapter(
			RabbitTemplate rabbitTemplate, 
			@Value("${exchangeName}") String exchangeName, 
			ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchangeName = exchangeName;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publish(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend(exchangeName,null, eventAsJson);		
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting object to json: %s".formatted(e.getMessage()));
		}

	}
}
