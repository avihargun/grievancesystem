package com.simformsolutions.grievance;

import com.simformsolutions.grievance.dto.ComplainDTO;
import com.simformsolutions.grievance.dto.RatingDTO;
import com.simformsolutions.grievance.dto.enums.Status;
import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.service.RatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class RatingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ComplainRepository complainRepository;

    @Autowired
    RatingService ratingService;

    @Autowired
    private ModelMapper modelMapper;

    RatingDTO ratingDTO=new RatingDTO(1L,9,"good");
    Complain record=new Complain(25L,"hello","address","12","description","photo",2L, Status.PENDING,"category",null);

    @Test
    void postRatingTest()
    {
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.ofNullable(record));
        record.setRating(modelMapper.map(ratingDTO,Rating.class));
        Mockito.when(complainRepository.save(any(Complain.class))).thenReturn(record);

        ComplainDTO actual=ratingService.saveRating(ratingDTO,25L);
        assertEquals(modelMapper.map(record,ComplainDTO.class),actual);
    }

    @Test
    void postRating_FailTest()
    {
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,() -> ratingService.saveRating(ratingDTO,25L));
    }
}
