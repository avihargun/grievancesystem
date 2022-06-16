package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ComplainController {

    @Autowired
    ComplainService complainService;

    @Autowired
    ComplainRepository complainRepository;


    @PostMapping("/complain")
    @ResponseBody
    public String postRegisterComplain(@ModelAttribute Complain complain, @RequestParam("complainerId") long id, @RequestParam("img") MultipartFile file1 )
    {

        complainService.saveComplain(complain,id,file1);
        return "success";
    }

    @GetMapping("/user/complains")
    public String getComplains(Model model, HttpServletRequest httpServletRequest)
    {

        model.addAttribute("complains",complainService.getUserComplains(httpServletRequest.getCookies()));
        return "myComplains";
    }
}
