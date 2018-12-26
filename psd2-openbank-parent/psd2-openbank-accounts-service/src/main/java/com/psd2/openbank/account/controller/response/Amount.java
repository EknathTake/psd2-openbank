package com.psd2.openbank.account.controller.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Amount {

	@JsonProperty("Amount")
	private BigDecimal amount;

	@JsonProperty("Currency")
	private String currency;

}
