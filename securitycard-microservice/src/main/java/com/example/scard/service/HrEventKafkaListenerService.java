package com.example.scard.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "messaging",havingValue = "kafka")
public class HrEventKafkaListenerService {

	@KafkaListener(topics = "${topicName}",groupId = "security-card")
	public void listenHrEvent(String event) {
		System.err.println("New event has arrived from Apache Kafka: %s.".formatted(event));
	}
	
	@KafkaListener(topics = "crm-events",groupId = "security-card")
	public void listenCrmEvent(String event) {
		System.err.println("New event has arrived from Apache Kafka: %s.".formatted(event));
	}
}
