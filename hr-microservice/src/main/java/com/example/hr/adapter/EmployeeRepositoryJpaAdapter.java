package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@Adapter(EmployeeRepository.class)
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository empRepo;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.empRepo = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean exists(TcKimlikNo kimlik) {
		return empRepo.existsById(kimlik.getValue());
	}

	@Override
	@Transactional
	public Employee persistEmployee(Employee employee) {
		var entity = modelMapper.map(employee, EmployeeEntity.class);
		return modelMapper.map(empRepo.save(entity),Employee.class);
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo kimlik) {
		return empRepo.findById(kimlik.getValue())
				      .map(entity -> modelMapper.map(entity, Employee.class));
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		empRepo.deleteById(employee.getIdentity().getValue());
	}

}
