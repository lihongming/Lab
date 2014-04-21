package com.asd.framework;

public abstract class ACustomer implements ICustomer {
	protected IAccount accounts;

	protected String name;
	protected String street;
	protected String city;
	protected String state;
	protected int zip;
	protected String email;

	public ACustomer() {
	}

	public ACustomer(String name, String street, String city, String state,
			int zip, String email) {
		super();
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;

		this.accounts = new Accounts();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void addAccount(IAccount account) {
		this.accounts.addAccount(account);
	}

	@Override
	public void removeAccount(IAccount account) {
	}

	@Override
	public IAccount getAccount() {
		return this.accounts.getAccount(0);
	}

	@Override
	public ICustomer getCustomer(String name) {
		if (this.name.equals(name)) {
			return this;
		}
		return null;
	}
}
