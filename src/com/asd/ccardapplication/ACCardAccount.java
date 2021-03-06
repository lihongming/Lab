package com.asd.ccardapplication;

import java.util.Date;

import com.asd.framework.AAccount;
import com.asd.framework.IAccount;
import com.asd.framework.ICustomer;
import com.asd.framework.IEntry;
import com.asd.framework.IFunctor;
import com.asd.framework.IPredicate;

public abstract class ACCardAccount extends AAccount {
	protected double monthly_interest = 0.0;
	protected double minimum_payment_rate = 0.0;
	protected double last_month_balance = 0.0;
	protected Date expire_date;

	public ACCardAccount(String ccnumber, Date expDate) {
		this.accnr = ccnumber;
		this.expire_date = expDate;
	}

	public String getCCnumber() {
		return this.accnr;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	@Override
	public void addAccount(IAccount account) {
	}

	@Override
	public void removeAccount(IAccount account) {
	}

	@Override
	public void addEntry(IEntry entry) {
		if (this.expire_date.compareTo(new Date()) > 0) {
			super.addEntry(entry);
		}
	}

	@Override
	public void addInterest() {
	}

	@Override
	public abstract String getInitial();

	@Override
	public double getLastMonthBalance() {
		IPredicate p = new LastMonthPredicate();
		IFunctor f = new SumFunctor();
		this.entries.doAll(p, f);
		return f.getValue();
	}

	@Override
	public double getTotalMonthlyCredit() {
		IPredicate p = new MonthlyCreditPredicate();
		IFunctor f = new SumFunctor();
		this.entries.doAll(p, f);
		return f.getValue();
	}

	@Override
	public double getTotalMonthlyCharge() {
		IPredicate p = new MonthlyChargePredicate();
		IFunctor f = new SumFunctor();
		this.entries.doAll(p, f);
		return f.getValue() * -1;
	}

	@Override
	public double getNewMonthlyBalance() {
		double lastBal = getLastMonthBalance();
		double totalCredit = getTotalMonthlyCredit();
		double totalCharge = getTotalMonthlyCharge();
		double newBal = lastBal - totalCredit + totalCharge
				+ this.monthly_interest * (lastBal - totalCredit);

		return newBal;
	}

	@Override
	public double getMonthlyAmountDue() {
		return this.minimum_payment_rate * getNewMonthlyBalance();
	}

	@Override
	public String generateMonthlyBills() {
		ICustomer c = getCustomer();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(String.format("Name= %s\n", c.getName()));
		strBuilder.append(String.format("Address= %s, %s, %s, %d\n",
				c.getStreet(), c.getCity(), c.getState(), c.getZip()));
		strBuilder.append(String.format("CC number= %s\n", getCCnumber()));
		strBuilder.append(String.format("CC type= %s\n", getInitial()));
		strBuilder.append(String.format("Previous balance= %.2f\n",
				getLastMonthBalance()));
		strBuilder.append(String.format("Total credits= %.2f\n",
				getTotalMonthlyCredit()));
		strBuilder.append(String.format("Total charges= %.2f\n",
				getTotalMonthlyCharge()));
		strBuilder.append(String.format("New balance= %.2f\n",
				getNewMonthlyBalance()));
		strBuilder.append(String.format("Total amount due= %.2f\n",
				getMonthlyAmountDue()));

		return strBuilder.toString();
	}
}
