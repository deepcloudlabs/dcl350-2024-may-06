package com.example.lottery.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.dto.response.LotteryModel;
import com.example.lottery.dto.response.LotteryResponse;

// curl -X POST "http://localhost:7100/api/v1/actuator/refresh" -d "{}" -H "Content-Type: application/json" -H "Accept: application/json"

@Service
@RefreshScope
public class LotteryService {
	private final int lotteryMax;
	private final int lotterySize;
	
	public LotteryService(@Value("${lotteryMax}") int lotteryMax,@Value("${lotterySize}") int lotterySize) {
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	public LotteryModel draw() {
		return new LotteryModel(ThreadLocalRandom.current()
				                .ints(1, lotteryMax)
				                .distinct()
				                .limit(lotterySize)
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
