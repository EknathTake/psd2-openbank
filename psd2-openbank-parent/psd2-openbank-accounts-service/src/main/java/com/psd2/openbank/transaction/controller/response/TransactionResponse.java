package com.psd2.openbank.transaction.controller.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionResponse {

	@JsonProperty("AccountId")
	private String accountId;

	@JsonProperty("TransactionId")
	private String transactionId;

	@JsonProperty("TransactionReference")
	private String transactionReference;

	@JsonProperty("StatementReference")
	private String[] statementReference;

	@JsonProperty("Amount")
	private Amount amount;

	@JsonProperty("CreditDebitIndicator")
	private String creditDebitIndicator;

	@JsonProperty("Status")
	private String status;

	/**
	 * "BookingDateTime":"2018-09-18T19:06:41.235Z",
	 */
	@JsonProperty("BookingDateTime")
	private Date bookingDateTime;

	/**
	 * "ValueDateTime":"2018-09-18T19:06:41.235Z",
	 */
	@JsonProperty("ValueDateTime")
	private Date valueDateTime;

	@JsonProperty("AddressLine")
	private String addressLine;

	@JsonProperty("BankTransactionCode")
	private BankTransactionCode bankTransactionCode;

	@JsonProperty("ProprietaryBankTransactionCode")
	private ProprietaryBankTransactionCode proprietaryBankTransactionCode;

	@JsonProperty("EquivalentAmount")
	private EquivalentAmount equivalentAmount;

	@JsonProperty("CreditorAgent")
	private Agent creditorAgent;

	@JsonProperty("DebtorAgent")
	private Agent debtorAgent;

	@JsonProperty("CardInstrument")
	private CardInstrument cardInstrument;

	@JsonProperty("TransactionInformation")
	private String transactionInformation;

	@JsonProperty("Balance")
	private Balance balance;

	@JsonProperty("MerchantDetails")
	private MerchantDetails merchantDetails;

	@JsonProperty("CreditorAccount")
	private Account creditorAccount;

	@JsonProperty("DebtorAccount")
	private Account debtorAccount;

}
