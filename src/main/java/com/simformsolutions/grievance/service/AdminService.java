package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.repository.ComplainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ComplainRepository complainRepository;

    public List<Complain> getComplains()
    {
        return complainRepository.findAll();
    }

    public Complain setComplainStatus(long id)
    {
        Optional<Complain> complain = complainRepository.findById(id);

        if(complain.isPresent())
        {
            complain.get().setStatus(1);
            return complain.get();
        }
        else
            throw new NoSuchElementException();
    }
}
