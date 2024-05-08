package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryConsumerService {
	private final String restApiUrl;
	private final RestTemplate restTemplate;
	
	
	public LotteryConsumerService(@Value("${restApiUrl}") String restApiUrl, RestTemplate restTemplate) {
		this.restApiUrl = restApiUrl;
		this.restTemplate = restTemplate;
	}


	@Scheduled(fixedRate = 3_000)
	public void consumeLotteryService() {
		var response = restTemplate.getForEntity(restApiUrl, String.class).getBody();
		System.out.println(response);
	}
}
