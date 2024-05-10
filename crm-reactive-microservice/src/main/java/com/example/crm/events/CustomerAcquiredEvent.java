package com.example.crm.events;

public class CustomerAcquiredEvent extends CrmBaseEvent {

	public CustomerAcquiredEvent(String identity) {
		super(CrmEventType.CUSTOMER_ACQUIRED_EVENT, identity);
	}

}
