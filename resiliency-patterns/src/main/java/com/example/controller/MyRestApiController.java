package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@RestController
@RequestMapping("/services")
@RequestScope
@CrossOrigin
public class MyRestApiController {

	@GetMapping
	@Bulkhead(name="restapi",type = Type.THREADPOOL)
	public String doService(){
		return "my response";
	}
}
