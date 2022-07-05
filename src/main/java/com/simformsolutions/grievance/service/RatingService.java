package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.dto.ComplainDTO;
import com.simformsolutions.grievance.dto.RatingDTO;
import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ComplainRepository complainRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ComplainDTO saveRating(RatingDTO ratingDTO , long id)
    {
        Complain complain= complainRepository.findById(id).orElseThrow(()->new NoSuchElementException("Complain not found with Id: "+id));
        complain.setRating( modelMapper.map(ratingDTO,Rating.class));
        return modelMapper.map(complainRepository.save(complain),ComplainDTO.class);

    }
}
