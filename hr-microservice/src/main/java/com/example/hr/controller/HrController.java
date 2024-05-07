package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hexagonal.Adapter;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.PhotoResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
@Validated
@Adapter
public class HrController {
    private final HrService hrService;
	
    public HrController(HrService hrService) {
		this.hrService = hrService;
	}

	// GET http://localhost:8100/hr/api/v1/employees/11111111110
    @GetMapping("{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
    	return hrService.getEmployeeById(identity);
	}

    // GET http://localhost:8100/hr/api/v1/employees/11111111110/photo
    @GetMapping("{identity}/photo")
    public PhotoResponse getEmployeePhoto(@PathVariable @TcKimlikNo String identity) {
    	return hrService.getEmployeePhoto(identity);
    }
	
    @PostMapping
    public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
    	return hrService.hireEmployee(request);
    }
    
    @DeleteMapping("{identity}")
    public EmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity) {
    	return hrService.fireEmployee(identity);
    }

}
