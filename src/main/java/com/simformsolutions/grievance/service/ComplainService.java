package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ComplainService {

    @Autowired
    private ComplainRepository complainRepository;

    @Autowired
    private UserRepository userRepository;

    public String saveComplain(Complain complain,long id)
    {
        User user= userRepository.findById(id).orElseThrow();
        complain.setStatus(0);
        user.getComplains().add(complain);
        return userRepository.save(user).toString();
    }
}
