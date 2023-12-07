package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
@ConditionalOnProperty(name="loadbalancing",havingValue = "manual")
public class LotteryConsumerService {
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private static final String LOTTERY_URL = "http://%s:%d/lottery/api/v1/numbers?column=5";
	private final AtomicInteger counter = new AtomicInteger();
	
	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void retrieveLotteryInstances() {
		this.instances = discoveryClient.getInstances("lottery");
		this.instances.forEach(System.err::println);
	}

	@Scheduled(fixedRate = 30_000)
	public void refreshLotteryServiceInstances() {
		this.instances = discoveryClient.getInstances("lottery");
		this.instances.forEach(System.err::println);
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var restTemplate= new RestTemplate();
		var instance = this.instances.get(counter.getAndIncrement()%this.instances.size());
		String url = LOTTERY_URL.formatted(instance.getHost(),instance.getPort());
		try {
			var response = restTemplate.getForEntity(url, String.class)
					.getBody();
			System.err.println("response from %s: %s".formatted(url,response));			
		}catch (Exception e) {
			this.instances = discoveryClient.getInstances("lottery");
			System.err.println("Error has occured: %s, new list has been retrieved.".formatted(e.getMessage()));
		}
	}
}
