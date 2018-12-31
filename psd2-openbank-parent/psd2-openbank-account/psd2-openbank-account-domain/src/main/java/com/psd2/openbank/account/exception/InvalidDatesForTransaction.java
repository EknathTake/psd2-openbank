package com.psd2.openbank.account.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvalidDatesForTransaction extends Exception {

	private int errorCode;

	private String message;

	public InvalidDatesForTransaction(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

}
