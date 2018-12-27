package com.psd2.openbank.transaction.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "ProprietaryBankTransactionCode":{ "Code":"P0008", "Issuer":"PI0008" },
 * 
 * @author eknath.take
 *
 */
@Data
public class ProprietaryBankTransactionCode {

	@JsonProperty("Code")
	private String code;

	@JsonProperty("Issuer")
	private String issuer;
}
