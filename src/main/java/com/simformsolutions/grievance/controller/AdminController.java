package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin/getcomplains")
    public String getAllComplains(Model model)
    {
        model.addAttribute("complains",adminService.getComplains());
        return "adminComplains";

    }

    @GetMapping("/admin/status/{id}")
    public String setComplainStatus(@PathVariable("id") long complainId)
    {
        adminService.setComplainStatus(complainId);
        return "redirect:"+"/admin/getcomplains";
    }

    @GetMapping("admin/rating/{id}")
    @ResponseBody
    public ResponseEntity<Rating> getRatingDetails(@PathVariable("id") long complainId)
    {
        return new ResponseEntity<>(adminService.getRating(complainId), HttpStatus.OK);
    }
}
