package com.psd2.openbank.account.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "CreditorAgent":{ "SchemeName":"BICFI", "Identification":"NoData" }
 * 
 * @author eknath.take
 *
 */

@Data
public class Agent {

	@JsonProperty("SchemeName")
	private String schemeName;

	@JsonProperty("Identification")
	private String identification;
}
