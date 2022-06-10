package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ComplainRepository complainRepository;

    public void saveRating(Rating rating , long id)
    {
        Complain complain= complainRepository.findById(id).orElseThrow();
        complain.setRating(rating);
        complainRepository.save(complain);

    }
}
