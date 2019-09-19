package com.assignment.demo.dto;

import com.assignment.demo.category.Category;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CategoryDTO implements Serializable {
    public CategoryDTO(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public static Set<CategoryDTO> mapDTO(Set<Category> categorySet) {
        Set<CategoryDTO> categoryDTOS = new HashSet<>();
        for (Category category : categorySet) {
            CategoryDTO dto = new CategoryDTO(category.getCategoryId(), category.getCategoryName());
            categoryDTOS.add(dto);
        }
        return categoryDTOS;
    }

    public int categoryId;
    public String categoryName;

}
