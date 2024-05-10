package com.example.crm.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.crm.event.CustomerEvent;

@Service
public class CustomerSimpleViewProjector {

	@EventListener
	public void listenEvent(CustomerEvent event) {
		
	}
}
