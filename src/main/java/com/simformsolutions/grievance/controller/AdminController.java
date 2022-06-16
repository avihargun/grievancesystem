package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin/getcomplains")
    public ResponseEntity<List<Complain>> getAllComplains()
    {
        return new ResponseEntity<>(adminService.getComplains(), HttpStatus.OK);
    }

    @GetMapping("/admin/status/{id}")
    public ResponseEntity<Complain> setComplainStatus(@PathVariable("id") long complainId)
    {
        return new ResponseEntity<>(adminService.setComplainStatus(complainId),HttpStatus.OK);
    }
}
