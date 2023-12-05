package com.example.hr.application.business.event;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.Employee;

@DomainEvent
public final class EmployeeFiredEvent extends HrEvent {
	private final Employee employee;

	public EmployeeFiredEvent(Employee employee) {
		super(employee.getIdentity(), EventType.FIRE_EVENT);
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

}
