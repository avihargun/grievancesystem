package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.repository.CategoryRepository;
import com.simformsolutions.grievance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model, HttpServletRequest httpServletRequest)
    {

        model.addAttribute("complainerId",userService.getComplainerId(httpServletRequest.getCookies()));
        model.addAttribute("categories",categoryRepository.findAll());

        return "dashboard";
    }

}
