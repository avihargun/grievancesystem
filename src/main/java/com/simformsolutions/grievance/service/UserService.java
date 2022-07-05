package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.dto.UserDTO;
import com.simformsolutions.grievance.exception.UserAlreadyExist;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.UserRepository;
import com.simformsolutions.grievance.util.JwtUtil;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<Object> saveUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new UserAlreadyExist("User with EmailId " + userDTO.getEmail() + " is Already Exist");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setUserId(userRepository.save(modelMapper.map(userDTO, User.class)).getUserId());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    public long getComplainerId(Cookie[] cookies) {
        String authorizationHeader = null;

        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                authorizationHeader = c.getValue();
            }
        }
        return userRepository.findByEmail(jwtUtil.extractUsername(authorizationHeader)).getUserId();
    }
}
