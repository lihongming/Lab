package com.asd.framework;

public class DepositCmd implements ICommand {
	private IAccount account;
	private double amount;

	public DepositCmd(IAccount c, double amount) {
		this.account = c;
		this.amount = amount;
	}

	@Override
	public void execute() {
		this.account.addEntry(new Entry(TransactionType.DEPOSIT, this.amount));
		this.account.notifyCustomer();
	}

}
