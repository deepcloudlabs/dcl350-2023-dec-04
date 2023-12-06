package com.example.hr.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.application.business.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Adapter(port=EventPublisher.class)
@Service
@ConditionalOnProperty(name = "use-messaging",havingValue = "rabbit")
public class RabbitEventPublisherService {
	private final RabbitTemplate rabbitTemplate;
	private final String exchangeName;
	private final ObjectMapper objectMapper;
	
	public RabbitEventPublisherService(
			RabbitTemplate rabbitTemplate, 
			@Value("${exchangeName}") String exchangeName, 
			ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchangeName = exchangeName;
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void publish(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend(exchangeName,null, eventAsJson);		
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting object to json: %s".formatted(e.getMessage()));
		}

	}
}
