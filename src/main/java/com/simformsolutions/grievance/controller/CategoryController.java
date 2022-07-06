package com.simformsolutions.grievance.controller;

import com.simformsolutions.grievance.entity.Category;
import com.simformsolutions.grievance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    @ResponseBody
    public String postCategory(@RequestParam("categoryName") List<String> names)
    {
        categoryService.saveCategory(
                names.stream().map(Category::new).toList()
        );
        return "ok";
    }

}
