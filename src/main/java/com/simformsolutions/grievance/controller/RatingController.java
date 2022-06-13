package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;


    @PostMapping("/rating")
    @ResponseBody
    public String postRating(@Valid @ModelAttribute Rating rating, @RequestParam("complainId") long id )
    {
        ratingService.saveRating(rating,id);

        return "ok";
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
