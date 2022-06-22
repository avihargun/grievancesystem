package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.entity.Category;
import com.simformsolutions.grievance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> saveCategory(List<Category> categories)
    {
        return categoryRepository.saveAll(categories.stream().
                filter(category->!(categoryRepository.existsByCategoryName(category.getCategoryName()))).
                collect(Collectors.toList()));
    }
}
