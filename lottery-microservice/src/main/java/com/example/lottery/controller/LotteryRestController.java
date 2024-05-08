package com.example.lottery.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.response.LotteryResponse;
import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
@CrossOrigin
@Validated
public class LotteryRestController {
	private final LotteryService lotteryService;
	
	
	public LotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}


	@GetMapping(params= {"column"})
	public LotteryResponse drawLottery(@RequestParam int column) {
		return lotteryService.draw(column);
	}
}
