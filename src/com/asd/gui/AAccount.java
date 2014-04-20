package com.asd.gui;

import java.util.ArrayList;

public abstract class AAccount implements IAccount {
	protected ICustomer customer;
	protected double balance;
	protected ArrayList<IEntry> entries;

	public AAccount() {
		this.entries = new ArrayList<IEntry>();
	}

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	@Override
	public void addEntry(IEntry entry) {
		this.entries.add(entry);
	}

	@Override
	public double getCurrentBalance() {
		return this.balance;
	}

	@Override
	public double getLastMonthBalance() {
		return 0; // need implementation
	}

	@Override
	public void notifyCustomer() {
		// need implementation
	}

	@Override
	public abstract void addInterest();

	@Override
	public abstract double getTotalMonthlyCredit();

	@Override
	public abstract double getTotalMonthlyCharge();

	@Override
	public abstract double getNewMonthlyBalance();

	@Override
	public abstract double getMonthlyAmountDue();

}