package com.simformsolutions.grievance.repository;

import com.simformsolutions.grievance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
