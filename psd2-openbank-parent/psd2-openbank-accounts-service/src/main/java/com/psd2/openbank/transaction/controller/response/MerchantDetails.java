package com.psd2.openbank.transaction.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "MerchantDetails":{ "MerchantName":"ACME Inc", "MerchantCategoryCode":5967 },
 * 
 * @author eknath.take
 *
 */

@Data
public class MerchantDetails {

	@JsonProperty("MerchantName")
	private String merchantName;

	@JsonProperty("MerchantCategoryCode")
	private String merchantCategoryCode;
}
