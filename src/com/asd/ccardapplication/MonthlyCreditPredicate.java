package com.asd.ccardapplication;

import java.util.Date;

import com.asd.framework.IEntry;
import com.asd.framework.IPredicate;
import com.asd.framework.TransactionType;

public class MonthlyCreditPredicate implements IPredicate {

	@Override
	public boolean check(IEntry e) {
		Date now = new Date();
		if (e.getDate().getMonth() == now.getMonth()
				&& e.getTransactionType() == TransactionType.DEPOSIT) {
			return true;
		}
		return false;
	}

}
