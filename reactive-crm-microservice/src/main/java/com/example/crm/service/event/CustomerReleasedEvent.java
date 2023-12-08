package com.example.crm.service.event;

public class CustomerReleasedEvent extends CustomerBaseEvent {

	public CustomerReleasedEvent(String identity) {
		super(identity,EventType.CUSTOMER_RELEASED_EVENT);
	}

}
