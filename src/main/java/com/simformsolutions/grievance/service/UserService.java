package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.Exception.UserAlreadyExist;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.UserRepository;
import com.simformsolutions.grievance.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<Object> saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
           throw new UserAlreadyExist();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public long getComplainerId( Cookie[] cookies)
    {
        String authorizationHeader = null;

        for(Cookie c :cookies )
        {
            if(c.getName().equals("token"))
            {
                authorizationHeader=c.getValue();
            }
        }
        return userRepository.findByEmail(jwtUtil.extractUsername(authorizationHeader)).getUserId();
    }
}
