package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.simformsolutions.grievance.entity.User user = userRepository.findByEmail(email);
        if(email.equals("admin@gmail.com"))
        {

            return new User(user.getEmail(), user.getPassword(), new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        }
        else
        {
            return new User(user.getEmail(), user.getPassword(), new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        }

        }
}
