package com.assignment.demo.Category;

import com.assignment.demo.category.Category;
import com.assignment.demo.category.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        Category category = new Category("CAT");
        categoryRepository.save(category);
    }

    @Test
    public void whenFindAll_thenReturnCategories() {
        // when
        List<Category> foundCategories = categoryRepository.findAll();

        // then
        assertNotNull(foundCategories);
    }
}
