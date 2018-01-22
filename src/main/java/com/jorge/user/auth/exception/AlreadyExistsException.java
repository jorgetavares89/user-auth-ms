package com.jorge.user.auth.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    private static final long serialVersionUID = -4233753300667670068L;
}
