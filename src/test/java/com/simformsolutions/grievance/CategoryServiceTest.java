package com.simformsolutions.grievance;

import com.simformsolutions.grievance.entity.Category;
import com.simformsolutions.grievance.repository.CategoryRepository;
import com.simformsolutions.grievance.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryServiceTest {

    @MockBean
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    Category category= new Category(1L,"category");

    @Test
    void postCategoryTest()
    {
        Mockito.when(categoryRepository.saveAll(anyList())).thenReturn(Arrays.asList(category));

        List<Category> actual= categoryService.saveCategory(Arrays.asList(category));
        assertEquals(Arrays.asList(category),actual);
    }

}
