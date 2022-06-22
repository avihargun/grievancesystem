package com.simformsolutions.grievance.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<Object> exception(UserAlreadyExist exception) {
        return new ResponseEntity<>("EmailId Already Exist", HttpStatus.BAD_REQUEST);
    }

}
