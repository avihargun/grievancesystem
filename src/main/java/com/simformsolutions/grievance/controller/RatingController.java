package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;


    @GetMapping("/rating/{id}")
    public String getRating(@PathVariable("id") String compId , Model model)
    {
        model.addAttribute("complainId",compId);
        return "rating";

    }

    @PostMapping("/rating")
    @ResponseBody
    public String postRating(@Valid @ModelAttribute Rating rating, @RequestParam("complainId") int id )
    {
        ratingService.saveRating(rating,id);

        return "success";
    }


}
