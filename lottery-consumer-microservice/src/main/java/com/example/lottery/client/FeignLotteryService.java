package com.example.lottery.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "lottery")
public interface FeignLotteryService {
	@GetMapping(value = "/lottery/api/v1/numbers", params = "column")
	public List<List<Integer>> draw(@RequestParam int column);
}
