package com.assignment.demo.category;

import com.assignment.demo.dto.CategoryDTO;
import com.assignment.demo.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList();
        for (Category category : categories) {
            categoryDTOList.add(new CategoryDTO(category.getCategoryId(), category.getCategoryName()));
        }
        return categoryDTOList;
    }

    public CategoryDTO addCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        Category addedCategory = categoryRepository.save(category);
        CategoryDTO result = new CategoryDTO(addedCategory.getCategoryId(), addedCategory.getCategoryName());
        return result;
    }

    public void deleteCategory(int categoryId) {
        if (!categoryRepository.findById(categoryId).isPresent())
            throw new CategoryNotFoundException(categoryId);

        categoryRepository.deleteById(categoryId);
    }

    public CategoryDTO addOrUpdateCategory(int categoryId, String categoryName) {
        Category result = updateCategory(categoryId, categoryName);
        return new CategoryDTO(result.getCategoryId(), result.getCategoryName());
    }

    private Category updateCategory(int categoryId, String categoryName) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setCategoryName(categoryName);
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    return categoryRepository.save(new Category(categoryName));
                });
    }
}
