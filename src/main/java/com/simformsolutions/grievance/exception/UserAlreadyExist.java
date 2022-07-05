package com.simformsolutions.grievance.exception;

import java.io.Serial;

public class UserAlreadyExist extends RuntimeException{

    @Serial
    private static final long serialVersionUID= 1L;

    public UserAlreadyExist(String message) {
        super(message);
    }
}
