package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ComplainController {

    @Autowired
    ComplainService complainService;


    @PostMapping("/complain")
    @ResponseBody
    public String postRegister(@ModelAttribute Complain complain, @RequestParam("complainerId") long id, @RequestParam("imgName") MultipartFile file1 )
    {
        complainService.saveComplain(complain,id);
        return "ok";
    }
}
