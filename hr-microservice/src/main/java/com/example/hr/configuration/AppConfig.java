package com.example.hr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class AppConfig {

	@Bean
	HrApplication createHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> publisher) {
		System.err.println(employeeRepository.getClass().getSimpleName());
		System.err.println(publisher.getClass().getSimpleName());
		return new StandardHrApplication(employeeRepository, publisher);
	}
}
