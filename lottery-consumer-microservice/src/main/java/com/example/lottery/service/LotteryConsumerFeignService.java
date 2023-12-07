package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.lottery.client.FeignLotteryService;

@Service
@ConditionalOnProperty(name="loadbalancing",havingValue = "feign")
public class LotteryConsumerFeignService {
	private final FeignLotteryService lotteryService;
	
	public LotteryConsumerFeignService(FeignLotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		System.err.println(lotteryService.draw(5));
	}
}
