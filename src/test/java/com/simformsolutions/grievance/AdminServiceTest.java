package com.simformsolutions.grievance;

import com.simformsolutions.grievance.dto.enums.Status;
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


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class AdminServiceTest {

    @MockBean
    ComplainRepository complainRepository;

    @Autowired
    AdminService adminService;

    Rating rating=new Rating(1L,9,"good");
    Complain record=new Complain(25L,"hello","address","12","description","photo",2L,Status.PENDING,"category",rating);

    @Test
    void getAllComplaintsTest(){

        Mockito.when(complainRepository.findAll()).thenReturn(Arrays.asList(record));

        List<Complain> result= adminService.getComplains();
        assertEquals(Arrays.asList(record),result);

    }

    @Test
    void setComplaintStatusTest() {
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.ofNullable(record));
        record.setStatus(Status.SOLVED);
        Mockito.when(complainRepository.save(any(Complain.class))).thenReturn(record);
        Complain result=adminService.setComplainStatus(25L);
        assertEquals(record,result);
    }

    @Test
    void getRatingDetailsTest(){
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.ofNullable(record));
        Rating rating1=adminService.getRating(25L);
        assertEquals(record.getRating(),rating1);
    }

    @Test
    void setComplainStatus_FailTest(){
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,() -> adminService.setComplainStatus(25L));

    }

}
