package com.example.hr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.application.business.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "use-messaging",havingValue = "kafka")
public class KafkaEventPublisherService {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String topicName;
	private final ObjectMapper objectMapper;

	public KafkaEventPublisherService(KafkaTemplate<String, String> kafkaTemplate,
			@Value("${topicName}") String topicName, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void listenHrEvents(HrEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(topicName, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting object to json: %s".formatted(e.getMessage()));
		}
	}
}
