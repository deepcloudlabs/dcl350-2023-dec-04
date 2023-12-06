package com.example.scard.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "messaging",havingValue = "rabbit")
public class HrEventRabbitListenerService {

	@RabbitListener(queues = "${queueName}")
	public void listenHrEvent(String event) {
		System.err.println("New event has arrived from RabbitMQ: %s.".formatted(event));
	}
}
