package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =
    context -> {
    	var employee = context.getSource();
    	var response = new EmployeeResponse();
    	response.setIdentity(employee.getIdentity().getValue());
    	response.setFirstName(employee.getFullname().firstName());
    	response.setLastName(employee.getFullname().lastName());
    	response.setIban(employee.getIban().value());
    	response.setSalary(employee.getSalary().getValue());
    	response.setCurrency(employee.getSalary().getCurrency());
    	response.setJobStyle(employee.getJobStyle());
    	response.setDepartments(employee.getDepartments());
    	response.setBirthYear(employee.getBirthYear().value());
    	return response;
    };			
    private static final Converter<Employee,HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER =
	context -> {
		var employee = context.getSource();
		var response = new HireEmployeeResponse();
		response.setIdentity(employee.getIdentity().getValue());
		response.setFirstName(employee.getFullname().firstName());
		response.setLastName(employee.getFullname().lastName());
		response.setIban(employee.getIban().value());
		response.setSalary(employee.getSalary().getValue());
		response.setCurrency(employee.getSalary().getCurrency());
		response.setJobStyle(employee.getJobStyle());
		response.setDepartments(employee.getDepartments());
		response.setBirthYear(employee.getBirthYear().value());
		return response;
	};			
	private static final Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER =
	context -> {
		var employee = context.getSource();
		var entity = new EmployeeEntity();
		entity.setIdentity(employee.getIdentity().getValue());
		entity.setFirstName(employee.getFullname().firstName());
		entity.setLastName(employee.getFullname().lastName());
		entity.setIban(employee.getIban().value());
		entity.setSalary(employee.getSalary().getValue());
		entity.setCurrency(employee.getSalary().getCurrency());
		entity.setJobStyle(employee.getJobStyle());
		entity.setDepartments(employee.getDepartments());
		entity.setBirthYear(employee.getBirthYear().value());
		return entity;
	};			
	
	private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =
	context -> {
		var request = context.getSource();
		return new Employee.Builder()
				.identity(request.getIdentity())
		        .fullname(request.getFirstName(),request.getLastName())
		        .iban(request.getIban())
		        .salary(request.getSalary(),request.getCurrency())
		        .departments(request.getDepartments())
		        .jobStyle(request.getJobStyle())
                .birthYear(request.getBirthYear())
                .photo(request.getPhoto())
		        .build();
	};			

	
	private static final Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER =
	context -> {
		var entity = context.getSource();
		return new Employee.Builder()
				.identity(entity.getIdentity())
		        .fullname(entity.getFirstName(),entity.getLastName())
		        .iban(entity.getIban())
		        .salary(entity.getSalary(),entity.getCurrency())
		        .departments(entity.getDepartments())
		        .jobStyle(entity.getJobStyle())
                .birthYear(entity.getBirthYear())
                .photo(entity.getPhoto())
		        .build();
	};
	
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		return modelMapper;
	}
}