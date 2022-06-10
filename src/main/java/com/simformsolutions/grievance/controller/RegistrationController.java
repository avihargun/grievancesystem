package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.RatingRepository;
import com.simformsolutions.grievance.service.ComplainService;
import com.simformsolutions.grievance.service.RatingService;
import com.simformsolutions.grievance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    ComplainService complainService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    @GetMapping("/register")
    public String getRegister()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String postRegister(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "ok";
    }

    @PostMapping("/complain")
    @ResponseBody
    public String postRegister(@ModelAttribute Complain complain,@RequestParam("complainerId") long id )
    {
        complainService.saveComplain(complain,id);
        return "ok";
    }

    @PostMapping("/rating")
    @ResponseBody
    public String postRating(@ModelAttribute Rating rating, @RequestParam("complainId") long id )
    {
        ratingService.saveRating(rating,id);
        return "ok";
    }

}
