package com.example.lottery.dto.response;

import java.util.List;

public class LotteryModel {
	private List<Integer> numbers;

	public LotteryModel() {
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "LotteryModel [numbers=" + numbers + "]";
	}

}
