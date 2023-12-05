package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.response.EmployeeResponse;

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
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		return modelMapper;
	}
}
