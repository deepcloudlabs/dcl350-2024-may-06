package com.example.hr.infra;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;

@Port(PortType.DRIVEN_PORT)
public interface EventPublisher<T> {

	void publishEvent(T event);

}
