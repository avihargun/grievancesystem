package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.RatingRepository;
import com.simformsolutions.grievance.service.ComplainService;
import com.simformsolutions.grievance.service.RatingService;
import com.simformsolutions.grievance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegister()
    {
        return "signUp";
    }

    @PostMapping("/register")
    @ResponseBody
    public String postRegister(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "ok";
    }

}
