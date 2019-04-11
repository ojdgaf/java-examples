package com.ojdgaf.examples.bootapp.controllers;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ojdgaf.examples.bootapp.exceptions.RegistrationException;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BadCredentialsException.class})
    public void handleBadCredentialsException(
            HttpServletResponse res,
            Exception e
    ) throws IOException {
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler({RegistrationException.class})
    public void handleRegistrationException(
            HttpServletResponse res,
            Exception e
    ) throws IOException {
        res.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({JwtException.class})
    public void handleJwtException(
            HttpServletResponse res,
            Exception e
    ) throws IOException {
        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public void handleEntityNotFoundException(
            HttpServletResponse res,
            Exception e
    ) throws IOException {
        res.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }
}
