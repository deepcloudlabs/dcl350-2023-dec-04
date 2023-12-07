package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.StandardLotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
@Validated
@CrossOrigin
public class LotteryRestController implements LotteryService {
	private final StandardLotteryService lotteryService;
	private final int serverPort;
	
	public LotteryRestController(StandardLotteryService lotteryService,@Value("${server.port}") int serverPort) {
		this.lotteryService = lotteryService;
		this.serverPort = serverPort;
	}

	@Override
	@GetMapping(params = "column")
	public List<List<Integer>> draw(@RequestParam int column) {
		System.err.println("New request has arrived to the server running at port %d".formatted(serverPort));
		return lotteryService.draw(column);
	}

	
}
