package com.asd.framework;

public class Company extends ACustomer implements ICompany {

	public Company(String name, String street, String city, String state,
			int zip, String email) {
		super(name, street, city, state, zip, email);
	}

	
	
	
	
	@Override
	public void addCustomer(ICustomer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCustomer(ICustomer customer) {
		// TODO Auto-generated method stub
		
	}

}