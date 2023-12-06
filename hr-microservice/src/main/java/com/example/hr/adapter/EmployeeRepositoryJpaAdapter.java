package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Adapter(port = EmployeeRepository.class)
@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		var employeeEntity = employeeEntityRepository.findById(identity.getValue());		
		return Optional.ofNullable(modelMapper.map(employeeEntity,Employee.class));
	}

	@Override
	public boolean existsByIdentity(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Employee create(Employee employee) {
		var employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
		var createdEmployeeEntity = employeeEntityRepository.save(employeeEntity);
		return modelMapper.map(createdEmployeeEntity, Employee.class);
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		TcKimlikNo identity = employee.getIdentity();
		employeeEntityRepository.deleteById(identity .getValue());
	}

}
