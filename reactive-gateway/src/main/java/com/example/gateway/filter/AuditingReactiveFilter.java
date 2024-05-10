package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuditingReactiveFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.err.println("Path: %s".formatted(request.getPath()));
		System.err.println("Method: %s".formatted(request.getMethod().name()));
		System.err.println("Remote Address: %s".formatted(request.getRemoteAddress()));
		System.err.println("URI: %s".formatted(request.getURI()));
		request.getBody().subscribe(System.err::println);
		request.getHeaders().forEach((name,value) -> System.err.println("%s: %s".formatted(name,value)));
		return chain.filter(exchange);
	}

}
