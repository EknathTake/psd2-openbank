package com.psd2.openbank.account.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "BankTransactionCode":{ "Code":"B0008", "SubCode":"BS0008" }
 * 
 * @author eknath.take
 *
 */
@Data
public class BankTransactionCode {

	@JsonProperty("Code")
	private String code;

	@JsonProperty("SubCode")
	private String subCode;

}
