package com.example.lottery.controller;

import java.util.List;

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

	public LotteryRestController(StandardLotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Override
	@GetMapping(params = "column")
	public List<List<Integer>> draw(@RequestParam int column) {
		return lotteryService.draw(column);
	}

	
}
