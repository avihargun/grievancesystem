package com.simformsolutions.grievance.repository;

import com.simformsolutions.grievance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    boolean existsByCategoryName(String category);

    Category findByCategoryName(String name);
}
