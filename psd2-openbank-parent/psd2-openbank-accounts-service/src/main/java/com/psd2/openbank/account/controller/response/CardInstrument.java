package com.psd2.openbank.account.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * "CardInstrument":{ "CardSchemeName":"AmericanExpress",
 * "AuthorisationType":"Contactless", "Name":"NoData", "Identification":"NoData"
 * },
 * 
 * @author eknath.take
 *
 */

@Data
public class CardInstrument {

	@JsonProperty("CardSchemeName")
	private String cardSchemeName;

	@JsonProperty("AuthorisationType")
	private String authorisationType;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Identification")
	private String identification;
}
