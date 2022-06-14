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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> postRegister(@ModelAttribute User user)
    {
        return userService.saveUser(user);

    }

    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> postLogin(@ModelAttribute User user)
    {
        return userService.saveUser(user);

    }

}
