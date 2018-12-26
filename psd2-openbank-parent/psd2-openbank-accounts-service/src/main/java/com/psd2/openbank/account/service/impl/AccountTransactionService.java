package com.psd2.openbank.account.service.impl;

import java.util.List;

import com.psd2.openbank.account.controller.response.TransactionResponse;

public interface AccountTransactionService {

	/**
	 * Service which returns Specific Account related Transactions.
	 * 
	 * @param accountId - A unique identifier used to identify the account resource.
	 * @return Account specific transactions
	 */
	List<TransactionResponse> getSpecificAccountTransactions(long accountId);
}
