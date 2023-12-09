package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class BusinessService {

	@Retry(name = "fun", fallbackMethod = "gun")
	public int fun() {
		if (ThreadLocalRandom.current().nextBoolean()) {
			System.err.println("fun() is about to fail...");
			throw new IllegalStateException("Ooops");
		}
		return 42;
	}

	@RateLimiter(name = "fun", fallbackMethod = "run")
	@Retry(name = "fun", fallbackMethod = "gun")
	public int sun() {
		return 42;
	}

	public int gun(Throwable e) {
		return 108;
	}

	public int run(Throwable e) {
		return 108;
	}
	
	@TimeLimiter(name="methoda",fallbackMethod = "methodAFallback")
	public CompletableFuture<Integer> methodA(){
		return CompletableFuture.supplyAsync(()->{
			try {TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 6));}catch (Exception e) {}
			return 42;
		});
	}

	public CompletableFuture<Integer> methodAFallback(Throwable e){
		return CompletableFuture.supplyAsync(()->{
			return 108;
		});
	}
}
