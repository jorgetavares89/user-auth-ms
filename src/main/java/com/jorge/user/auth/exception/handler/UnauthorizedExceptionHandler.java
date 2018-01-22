package com.jorge.user.auth.exception.handler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.jorge.user.auth.exception.CustomExceptionHandler;
import com.jorge.user.auth.exception.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UnauthorizedExceptionHandler extends CustomExceptionHandler {

    @ExceptionHandler(value = {UnauthorizedException.class, JWTDecodeException.class})
    protected ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                getErrorResponse(ex, HttpStatus.UNAUTHORIZED),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                request);
    }
}
