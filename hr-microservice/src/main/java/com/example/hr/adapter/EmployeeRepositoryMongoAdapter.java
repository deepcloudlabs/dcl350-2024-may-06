package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hexagonal.Adapter;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@Adapter(EmployeeRepository.class)
@ConditionalOnProperty(name="persistenceStrategy", havingValue = "mongodb")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository employeeDocumentRepository;
	private final ModelMapper modelMapper;

	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository employeeDocumentRepository,
			ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean exists(TcKimlikNo kimlik) {
		return employeeDocumentRepository.existsById(kimlik.getValue());
	}

	@Override
	public Employee persistEmployee(Employee employee) {
		var document = modelMapper.map(employee, EmployeeDocument.class);
		var persistedDocument = employeeDocumentRepository.insert(document);
		return modelMapper.map(persistedDocument, Employee.class);
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo kimlik) {
		return employeeDocumentRepository.findById(kimlik.getValue())
				.map(document -> modelMapper.map(document, Employee.class));
	}

	@Override
	public void remove(Employee employee) {
		employeeDocumentRepository.deleteById(employee.getIdentity().getValue());
	}

}
