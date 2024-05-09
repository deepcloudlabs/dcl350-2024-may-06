package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.PhotoResponse;

@Service
@ConditionalOnProperty(name="persistenceStrategy", havingValue = "mongodb")
public class NtHrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;
	
	public NtHrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Cacheable
	public EmployeeResponse getEmployeeById(String identity) {
		var entity = hrApplication.getEmployee(TcKimlikNo.of(identity))
				                  .orElseThrow(() -> new IllegalArgumentException("Employee[%s] not found!".formatted(identity)));
		return modelMapper.map(entity, EmployeeResponse.class);
	}


	public PhotoResponse getEmployeePhoto(String identity) {
		var entity = hrApplication.getEmployee(TcKimlikNo.of(identity))
				.orElseThrow(() -> new IllegalArgumentException("Employee[%s] not found!".formatted(identity)));
		return new PhotoResponse(entity.getPhoto().toBase64());
	}

	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var persistedEmployee = hrApplication.hireEmployee(employee);
		return modelMapper.map(persistedEmployee, HireEmployeeResponse.class);
	}

	public EmployeeResponse fireEmployee(String identity) {
		var employee = hrApplication.fireEmployee(TcKimlikNo.of(identity));
		return modelMapper.map(employee, EmployeeResponse.class);
	}

}
