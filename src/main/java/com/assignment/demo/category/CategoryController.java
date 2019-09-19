package com.assignment.demo.category;

import com.assignment.demo.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CategoryDTO> addProduct(@RequestParam(value = "category-name") String categoryName) {
        CategoryDTO categoryDTO = categoryService.addCategory(categoryName);
        return new ResponseEntity(categoryDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<CategoryDTO> updateCategory(@RequestParam(value = "category-id") int categoryId,
                                               @RequestParam(value = "category-name") String categoryName) {

        CategoryDTO categoryDTO = categoryService.addOrUpdateCategory(categoryId, categoryName);
        return new ResponseEntity(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public void deleteProduct(@RequestParam(value = "category-id") int categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
