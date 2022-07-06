package com.simformsolutions.grievance.exception;

import com.simformsolutions.grievance.dto.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalException {

    Logger logger= LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> exception(NoSuchElementException exception){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.name(),exception.getMessage(), LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
        logger.debug("handling NoSuchElementException...");
        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialException(BadCredentialsException exception)
    {
        ErrorResponse errorResponse= new ErrorResponse(HttpStatus.FORBIDDEN.name(), exception.getMessage(),LocalDateTime.now(),HttpStatus.FORBIDDEN.value());
        logger.debug("handling BadCredentialsException...");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, String> validationHandler(BindException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        logger.debug("handling BindException...");
        return errors;

    }
}
