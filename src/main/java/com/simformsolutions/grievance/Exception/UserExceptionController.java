package com.simformsolutions.grievance.Exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<Object> exception(UserAlreadyExist exception) {
        return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
    }

}
