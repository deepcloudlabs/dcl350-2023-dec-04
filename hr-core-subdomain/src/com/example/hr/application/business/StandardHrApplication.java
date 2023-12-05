package com.example.hr.application.business;

import java.util.Optional;

import com.example.ddd.Application;
import com.example.hr.application.HrApplication;
import com.example.hr.application.business.event.EmployeeFiredEvent;
import com.example.hr.application.business.event.EmployeeHiredEvent;
import com.example.hr.application.business.event.HrEvent;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Application(port=HrApplication.class)
public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEvent> eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Optional<Employee> getEmployee(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getIdentity();
		if(employeeRepository.existsByIdentity(identity))
			throw new ExistingEmployeeException("Employee already exists",identity);
        Employee createdEmployee = employeeRepository.create(employee);
		var event = new EmployeeHiredEvent(identity);
		eventPublisher.publish(event);
        return createdEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo identity) {
		var employeeFound = employeeRepository.findEmployeeByIdentity(identity)
				                              .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists",identity));
		employeeRepository.remove(employeeFound);
		var event = new EmployeeFiredEvent(employeeFound);
		eventPublisher.publish(event);
		return employeeFound;
	}

}
