package com.asd.framework;

import java.util.ArrayList;

public class Customers extends ACustomer {
	
	protected ArrayList<ICustomer> customers;
	

	public Customers(String name, String street, String city, String state,
			int zip, String email) {
		super(name, street, city, state, zip, email);
		
		this.customers = new ArrayList<>();
	}


	@Override
	public void addCustomer(ICustomer customer) {
		
		customers.add(customer);
		
	}


	@Override
	public void removeCustomer(ICustomer customer) {
		// TODO Auto-generated method stub
		
	}

}