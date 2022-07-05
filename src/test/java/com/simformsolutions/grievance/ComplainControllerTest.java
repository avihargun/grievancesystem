package com.simformsolutions.grievance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.simformsolutions.grievance.dto.ComplainDTO;
import com.simformsolutions.grievance.dto.enums.Status;
import com.simformsolutions.grievance.service.ComplainService;
import com.simformsolutions.grievance.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.servlet.http.Cookie;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ComplainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommonsMultipartResolver commonsMultipartResolver;
    private final JwtUtil jwtUtil=new JwtUtil();

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @MockBean
    ComplainService complainService;

    MockMultipartFile first= new MockMultipartFile("data","photo.jpg","text/plain","some.xml".getBytes());
    ComplainDTO record=new ComplainDTO(25L,"hello","address","12","description","photo",2L, Status.SOLVED,"category",null);

    Cookie[] cookie= new Cookie[]{new Cookie("token",jwtUtil.generateToken("mohitdavera@gmail.com"))};
    @Test
    void registerComplainTest() throws Exception {


 //       List<Category> records=new ArrayList<>(Arrays.asList(category));
        Mockito.when(complainService.saveComplain(record,3L,first)).thenReturn(String.valueOf(record));

        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/complain")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("complainerId",String.valueOf(3))
                .content(objectWriter.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk()).andExpect(content().string("success"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void getComplainTest() throws Exception{

        Mockito.when(complainService.getUserComplains(cookie)).thenReturn(Arrays.asList(record));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/complains").cookie(cookie)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }
}
