package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.dto.enums.Status;
import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.repository.ComplainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ComplainRepository complainRepository;

    @Autowired
    ModelMapper modelMapper;


    public List<Complain> getComplains()
    {
        return complainRepository.findAll();
    }

    public Complain setComplainStatus(long id)
    {
        Optional<Complain> complain = complainRepository.findById(id);

        if(complain.isPresent())
        {
            complain.get().setStatus(Status.SOLVED);
            return complainRepository.save(complain.get());
        }
        else
            throw new NoSuchElementException("Complain not found with Id: "+id);
    }

    public Rating getRating(long complainId) {

        Optional<Complain> complain= complainRepository.findById(complainId);
        if(complain.isPresent())
        {
            return complain.get().getRating();
        }
        else throw new NoSuchElementException("Rating not found for complainId: " + complainId);
    }
}
