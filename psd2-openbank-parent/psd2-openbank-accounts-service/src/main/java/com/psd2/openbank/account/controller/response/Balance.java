package com.psd2.openbank.account.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "Balance":{ "Amount":{ "Amount":"800.00", "Currency":"GBP" },
 * "CreditDebitIndicator":"Credit", "Type":"transaction" },
 * 
 * @author eknath.take
 *
 */

@Data
public class Balance {

	@JsonProperty("Amount")
	private Amount amount;

	@JsonProperty("CreditDebitIndicator")
	private String creditDebitIndicator;

	@JsonProperty("Type")
	private String type;
}
