package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
	List<EmployeeEntity> findAllByJobStyle(JobStyle jobStyle);
	List<EmployeeEntity> findAllByBirthYearBetween(int beginYear,int endYear);
	List<EmployeeEntity> findAllByDepartmentsInAndBirthYearBetween(List<Department> departments,int beginYear,int endYear);
}
