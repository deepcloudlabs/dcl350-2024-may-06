package com.example.lottery.dto.response;

import java.util.List;

public class LotteryResponse {
	private List<LotteryModel> numbers;

	public LotteryResponse() {
	}

	public void setNumbers(List<LotteryModel> numbers) {
		this.numbers = numbers;
	}

	public List<LotteryModel> getNumbers() {
		return numbers;
	}

	@Override
	public String toString() {
		return "LotteryResponse [numbers=" + numbers + "]";
	}

}
