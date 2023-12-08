package com.example.crm.service.event;

public class CustomerAcquiredEvent extends CustomerBaseEvent {

	public CustomerAcquiredEvent(String identity) {
		super(identity,EventType.CUSTOMER_ACQUIRED_EVENT);
	}

}
