package com.example.crm.events;

import com.example.crm.entity.Customer;

public class CustomerReleasedEvent extends CrmBaseEvent {
	private final Customer customer;

	public CustomerReleasedEvent(Customer customer) {
		super(CrmEventType.CUSTOMER_RELEASED_EVENT, customer.getIdentity());
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

}
