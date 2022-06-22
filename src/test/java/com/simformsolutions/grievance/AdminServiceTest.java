package com.simformsolutions.grievance;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.service.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AdminServiceTest {

    @MockBean
    ComplainRepository complainRepository;

    @Autowired
    AdminService adminService;

    Rating rating=new Rating(1L,9,"good");
    Complain record=new Complain(25L,"hello","address","12","description","photo",2L,0,"category",rating);

    @Test
    public void getAllComplaintsTest(){

        Mockito.when(complainRepository.findAll()).thenReturn(Arrays.asList(record));

        List<Complain> result= adminService.getComplains();
        assertEquals(Arrays.asList(record),result);

    }

    @Test
    public void setComplaintStatusTest() {
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.ofNullable(record));
        record.setStatus(1);
        Mockito.when(complainRepository.save(any(Complain.class))).thenReturn(record);
        Complain result=adminService.setComplainStatus(25L);
        assertEquals(record,result);
    }

    @Test
    public void getRatingDetailsTest(){
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.ofNullable(record));
        Rating rating1=adminService.getRating(25L);
        assertEquals(record.getRating(),rating1);
    }

}
