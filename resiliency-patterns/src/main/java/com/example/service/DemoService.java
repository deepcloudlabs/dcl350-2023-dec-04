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
		System.out.println("Response from business service: %d".formatted(businessService.fun()));
	}
}
