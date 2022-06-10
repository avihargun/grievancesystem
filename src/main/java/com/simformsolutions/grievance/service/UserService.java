package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user)
    {
        userRepository.save(user);
    }
}
