package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	private final BusinessService businessService;
	
	public DemoService(BusinessService businessService) {
		this.businessService = businessService;
	}

	@Scheduled(fixedRate = 300)
	public void callBusinessMethod() {
		businessService.methodA()
		.thenAcceptAsync(number -> System.err.println("Number is received: %d".formatted(number)));
		//System.out.println("Response from business service: %d".formatted(businessService.sun()));
	}
}
