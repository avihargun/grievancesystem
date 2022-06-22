package com.simformsolutions.grievance;

import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.Rating;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.service.RatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ComplainRepository complainRepository;

    @Autowired
    RatingService ratingService;

    Rating rating=new Rating(1L,9,"good");
    Complain record=new Complain(25L,"hello","address","12","description","photo",2L,0,"category",null);

    @Test
    public void postRatingTest()
    {
        Mockito.when(complainRepository.findById(25L)).thenReturn(Optional.ofNullable(record));
        record.setRating(rating);
        Mockito.when(complainRepository.save(record)).thenReturn(record);

        Complain actual=ratingService.saveRating(rating,25L);
        assertEquals(record,actual);
    }
}
