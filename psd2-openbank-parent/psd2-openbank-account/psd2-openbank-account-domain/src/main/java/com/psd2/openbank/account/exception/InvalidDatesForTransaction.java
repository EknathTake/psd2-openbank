package com.psd2.openbank.account.exception;

public class InvalidDatesForTransaction extends Exception {

	private int errorCode;

	private String message;

	public InvalidDatesForTransaction(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

}
