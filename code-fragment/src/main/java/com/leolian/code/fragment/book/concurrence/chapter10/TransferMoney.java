package com.leolian.code.fragment.book.concurrence.chapter10;

import com.leolian.code.fragment.book.concurrence.chapter10.entity.Account;
import com.leolian.code.fragment.book.concurrence.chapter10.entity.DollarAmount;

public class TransferMoney {
	private static final Object TIE_LOCK = new Object();

	public static void transferMoney(final Account fromAcct, final Account toAcct, final DollarAmount amount) throws InsufficientFundsException {
		class Helper {
			public void transfer() throws InsufficientFundsException {
				if(fromAcct.getBalance().compareTo(amount) < 0)
					throw new InsufficientFundsException();
				else {
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}
			}
		}
		int fromHash = System.identityHashCode(fromAcct);
		int toHash = System.identityHashCode(toAcct);
		
		if(fromHash < toHash) {
			synchronized (fromAcct) {
				synchronized (toAcct) {
					new Helper().transfer();
				}
			}
		} else if (fromHash > toHash) {
			synchronized (toAcct) {
				synchronized (fromAcct) {
					new Helper().transfer();
				}
			}
		} else {
			synchronized (TIE_LOCK) {
				synchronized (fromAcct) {
					synchronized (toAcct) {
						new Helper().transfer();
					}
				}
			}
		}
		
	}

}