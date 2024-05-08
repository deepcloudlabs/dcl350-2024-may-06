package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
public class LotteryConsumerService {
	private final String restApiUrl;
	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private final AtomicInteger counter = new AtomicInteger();;

	public LotteryConsumerService(@Value("${restApiUrl}") String restApiUrl, RestTemplate restTemplate,
			DiscoveryClient discoveryClient) {
		this.restApiUrl = restApiUrl;
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void readInstanceList() {
		this.instances = discoveryClient.getInstances("lottery");
	}

	@Scheduled(fixedRate = 3_000)
	public void consumeLotteryService() {
		var instance = getNextInstance();
		var host = instance.getHost();
		var port = instance.getPort();
		var url = restApiUrl.formatted(host, port);
		try {
			var response = restTemplate.getForEntity(url, String.class).getBody();
			System.out.println("Received response from %s: %s".formatted(url, response));
		} catch (Exception e) {
			System.err.println("Error has occured: %s".formatted(e.getMessage()));
			readInstanceList();
		}
	}

	private ServiceInstance getNextInstance() {
		return instances.get(counter.getAndIncrement() % instances.size());
	}
}
