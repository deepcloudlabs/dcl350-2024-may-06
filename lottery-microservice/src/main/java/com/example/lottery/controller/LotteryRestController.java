package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Value;
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
	private final int serverPort;

	public LotteryRestController(LotteryService lotteryService, @Value("${server.port}") int serverPort) {
		this.lotteryService = lotteryService;
		this.serverPort = serverPort;
	}

	@GetMapping(params = { "column" })
	public LotteryResponse drawLottery(@RequestParam int column) {
		System.err.println("Received a new request at port %d".formatted(serverPort));
		return lotteryService.draw(column);
	}
}
