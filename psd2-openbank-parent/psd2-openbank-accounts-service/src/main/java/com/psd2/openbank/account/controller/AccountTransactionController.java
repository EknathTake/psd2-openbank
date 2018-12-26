package com.psd2.openbank.account.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountTransactionController {

	/**
	 * To make a call to the Specific Account Transactions API, you will need to
	 * know the interaction ID, financial ID as well as the account-specific access
	 * token, as granted by the account holder. This access token can be retrieved
	 * via the APIs found in the Authorization collection.
	 * 
	 * @param AccountId - A unique identifier used to identify the account resource.
	 */
	@GetMapping(path = "/apis/retailbanking/v2.0/accounts/{AccountId}/transactions")
	public void specificAccountTransactions(@PathVariable(name = "AccountId") String accountId) {

	}

}
