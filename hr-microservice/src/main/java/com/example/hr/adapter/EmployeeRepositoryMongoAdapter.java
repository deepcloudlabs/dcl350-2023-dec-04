package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexagonal.Adapter;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Adapter(port = EmployeeRepository.class)
@Repository
@ConditionalOnProperty(name="persistence",havingValue = "mongo")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository employeeDocumentRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository employeeDocumentRepository, ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		var employeeEntity = employeeDocumentRepository.findById(identity.getValue());		
		return Optional.ofNullable(modelMapper.map(employeeEntity,Employee.class));
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return employeeDocumentRepository.existsById(identity.getValue());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Employee create(Employee employee) {
		var employeeDocument = modelMapper.map(employee, EmployeeDocument.class);
		var createdEmployeeEntity = employeeDocumentRepository.insert(employeeDocument);
		return modelMapper.map(createdEmployeeEntity, Employee.class);
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		TcKimlikNo identity = employee.getIdentity();
		employeeDocumentRepository.deleteById(identity .getValue());
	}

}
