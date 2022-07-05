package com.simformsolutions.grievance;

import com.simformsolutions.grievance.entity.Category;
import com.simformsolutions.grievance.repository.CategoryRepository;
import com.simformsolutions.grievance.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class DashboardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private UserService userService;

    Category category=new Category(1L,"Cat");

    @Test
    void getDashboardTest() throws Exception {

        List<Category> records=new ArrayList<>(Arrays.asList(category));
        Mockito.when(categoryRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/dashboard")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }


}
