package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.dto.UserDTO;
import com.simformsolutions.grievance.service.UserService;
import com.simformsolutions.grievance.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/register")
    public String getRegister()
    {
        return "signUp";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute UserDTO userDTO)
    {
        userService.saveUser(userDTO);
        return "redirect:" +"/login";

    }

    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute UserDTO userDTO, HttpServletResponse httpServletResponse)
    {

        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));

        Cookie cookie = new Cookie("token",jwtUtil.generateToken(userDTO.getEmail()));
        cookie.setMaxAge(60 * 60 * 10);
        httpServletResponse.addCookie(cookie);

        return "redirect:" +"/dashboard";

    }

}
