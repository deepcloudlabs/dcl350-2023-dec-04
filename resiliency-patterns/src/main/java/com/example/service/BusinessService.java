package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class BusinessService {
	@Retry(name = "fun",fallbackMethod = "gun")
	public int fun() {
		if (ThreadLocalRandom.current().nextBoolean()) {
			System.err.println("fun() is about to fail...");
			throw new IllegalStateException("Ooops");
		}
		return 42;
	}
	
	public int gun(Throwable e) {
		return 108;
	}
}
