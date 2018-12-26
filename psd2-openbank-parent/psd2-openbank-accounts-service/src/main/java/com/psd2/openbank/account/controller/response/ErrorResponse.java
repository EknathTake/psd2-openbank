package com.psd2.openbank.account.controller.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorResponse implements Serializable {

	private String errorCode;

	private String message;

}
