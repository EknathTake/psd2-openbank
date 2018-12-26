package com.psd2.openbank.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class Psd2OpenBankInvalidJwtAuthenticationException extends AuthenticationException {
    public Psd2OpenBankInvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
