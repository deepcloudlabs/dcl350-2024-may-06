package com.example.lottery.dto.response;

import java.util.List;

public class LotteryResponse {
	private final List<LotteryModel> numbers;

	public LotteryResponse(List<LotteryModel> numbers) {
		this.numbers = numbers;
	}

	public List<LotteryModel> getNumbers() {
		return numbers;
	}

}
