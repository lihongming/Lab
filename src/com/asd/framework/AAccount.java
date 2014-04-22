package com.asd.framework;

import java.util.ArrayList;

public abstract class AAccount implements IAccount {
	protected ICustomer customer;
	protected String accnr;
	protected double current_balance;
	protected ArrayList<IEntry> entries = new ArrayList<>();

	@Override
	public void addAccount(IAccount account) {
	}

	@Override
	public ICustomer getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	@Override
	public void addEntry(IEntry entry) {
		this.current_balance += entry.getAmount();
		this.entries.add(entry);
	}

	@Override
	public double getCurrentBalance() {
		return this.current_balance;
	}

	@Override
	public void notifyCustomer(double amount) {
		getCustomer().sendEmailToCustomer(amount);
	}

	@Override
	public String generateMonthlyBills() {
		return null;
	}

	@Override
	public abstract void addInterest();

	@Override
	public abstract String getType();

	@Override
	public abstract double getLastMonthBalance();

	@Override
	public abstract double getTotalMonthlyCredit();

	@Override
	public abstract double getTotalMonthlyCharge();

	@Override
	public abstract double getNewMonthlyBalance();

	@Override
	public abstract double getMonthlyAmountDue();

}
