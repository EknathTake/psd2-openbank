package com.psd2.openbank.account.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "EquivalentAmount":{ "Amount":{ "Amount":"NoData", "Currency":"NoData" },
 * "CurrencyOfTransfer":"NoData" },
 * 
 * @author eknath.take
 *
 */
@Data
public class EquivalentAmount {

	@JsonProperty("Amount")
	private Amount amount;

	@JsonProperty("CurrencyOfTransfer")
	private String currencyOfTransfer;

}
