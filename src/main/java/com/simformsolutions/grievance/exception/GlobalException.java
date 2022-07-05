package com.simformsolutions.grievance.exception;

import com.simformsolutions.grievance.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> exception(NoSuchElementException exception){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.name(),exception.getMessage(), LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, String> validationHandler(BindException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;

    }
}
