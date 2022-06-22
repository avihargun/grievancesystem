package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.service.UserService;
import com.simformsolutions.grievance.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public String postRegister(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return "redirect:" +"/login";

    }

    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute User user, HttpServletResponse httpServletResponse) throws Exception
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");


        }

        Cookie cookie = new Cookie("token",jwtUtil.generateToken(user.getEmail()));
        cookie.setMaxAge(60 * 60 * 10);
        httpServletResponse.addCookie(cookie);

        return "redirect:" +"/dashboard";

    }

}
