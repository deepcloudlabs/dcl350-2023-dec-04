package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.application.business.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher<HrEvent> {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String topicName;
	private final ObjectMapper objectMapper;
	
	public EventPublisherKafkaAdapter(
			KafkaTemplate<String, String> kafkaTemplate, 
			@Value("${topicName}") String topicName, 
			ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publish(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(topicName, eventAsJson);		
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting object to json: %s".formatted(e.getMessage()));
		}

	}
}
