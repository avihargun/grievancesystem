package com.simformsolutions.grievance.repository;

import com.simformsolutions.grievance.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
