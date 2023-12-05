package com.example.hr.repository;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVEN)
public interface EmployeeRepository {

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	boolean existsByIdentity(TcKimlikNo identity);

	Employee create(Employee employee);

	void remove(Employee employee);

}
