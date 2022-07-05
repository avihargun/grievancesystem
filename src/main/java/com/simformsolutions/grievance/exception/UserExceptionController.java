package com.simformsolutions.grievance.exception;

import com.simformsolutions.grievance.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<Object> exception(UserAlreadyExist exception) {
        ErrorResponse errorResponse= new ErrorResponse(HttpStatus.BAD_REQUEST.name(),exception.getMessage(),LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
