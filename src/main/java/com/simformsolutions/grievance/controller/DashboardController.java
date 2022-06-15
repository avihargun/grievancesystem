package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/dashboard")
    public String getDashboard(Model model)
    {
        model.addAttribute("categories",categoryRepository.findAll());
        return "dashboard";
    }

}
