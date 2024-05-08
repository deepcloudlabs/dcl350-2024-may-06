package com.example.lottery.dto.response;

import java.util.List;

public class LotteryModel {
	private final List<Integer> numbers;

	public LotteryModel(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

}
