package com.example.lottery.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lottery.dto.response.LotteryResponse;

@FeignClient(name = "lottery", path = "/api/v1/numbers")
public interface LotteryService {
	@GetMapping(params= {"column"})
	public LotteryResponse drawLottery(@RequestParam int column) ;
}
