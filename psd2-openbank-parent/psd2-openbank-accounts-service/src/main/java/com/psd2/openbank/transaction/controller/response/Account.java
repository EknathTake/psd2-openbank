package com.psd2.openbank.transaction.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "CreditorAccount":{ "SchemeName":"IBAN", "Identification":"NoData",
 * "Name":"NoData", "SecondaryIdentification":"NoData" },
 * 
 * @author eknath.take
 *
 */

@Data
public class Account {

	@JsonProperty("SchemeName")
	private String schemeName;

	@JsonProperty("Identification")
	private String identification;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("SecondaryIdentification")
	private String secondaryIdentification;
}
