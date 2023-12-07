package com.example.lottery.service;

import java.util.List;

public interface LotteryService {
	List<List<Integer>> draw(int column);
}
