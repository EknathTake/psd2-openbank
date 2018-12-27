package com.psd2.openbank.account.service;

import java.util.List;

import com.psd2.openbank.transaction.controller.response.TransactionResponse;

public interface TransactionService {

	/**
	 * Service which returns Specific Account related Transactions.
	 * 
	 * @param accountId - A unique identifier used to identify the account resource.
	 * @return Account specific transactions
	 */
	List<TransactionResponse> getSpecificAccountTransactions(long accountId);
}
