package com.ojdgaf.examples.bootapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistrationException extends RuntimeException {
    public RegistrationException() {
        super();
    }

    public RegistrationException(String message) {
        super(message);
    }
}
