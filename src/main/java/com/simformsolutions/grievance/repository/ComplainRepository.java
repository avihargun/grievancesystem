package com.simformsolutions.grievance.repository;

import com.simformsolutions.grievance.entity.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
}
