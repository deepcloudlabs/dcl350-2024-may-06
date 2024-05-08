package com.example.lottery.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.dto.response.LotteryModel;
import com.example.lottery.dto.response.LotteryResponse;

@Service
public class LotteryService {

	public LotteryModel draw() {
		return new LotteryModel(ThreadLocalRandom.current()
				                .ints(1, 60)
				                .distinct()
				                .limit(6)
				                .sorted()
				                .boxed()
				                .toList());
	}
	
	public LotteryResponse draw(int column) {
		return new LotteryResponse(
				IntStream.range(0, column)
				         .mapToObj(i -> draw())
				         .toList()
				);
	}

}
