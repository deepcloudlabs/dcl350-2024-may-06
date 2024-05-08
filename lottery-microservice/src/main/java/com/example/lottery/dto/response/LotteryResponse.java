package com.example.lottery.dto.response;

import java.util.List;

public class LotteryResponse {
	private List<LotteryModel> numbers;

	public LotteryResponse() {
	}

	public LotteryResponse(List<LotteryModel> numbers) {
		this.numbers = numbers;
	}

	public List<LotteryModel> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<LotteryModel> numbers) {
		this.numbers = numbers;
	}

}
