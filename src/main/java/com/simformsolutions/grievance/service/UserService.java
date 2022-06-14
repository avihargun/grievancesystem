package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.Exception.UserAlreadyExist;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
           throw new UserAlreadyExist();
        }

        userRepository.save(user);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
