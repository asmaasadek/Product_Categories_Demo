package com.assignment.demo.Category;

import com.assignment.demo.category.Category;
import com.assignment.demo.category.CategoryRepository;
import com.assignment.demo.category.CategoryService;
import com.assignment.demo.dto.CategoryDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    private Category category;

    @Before
    public void setUp() {
        category = new Category("CAT");
        category.setCategoryId(1);

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category);
    }


    @Test
    public void whenUpdateCategory_thenUpdatedShouldReturn() {
        //given
        String newCategoryName = "NEW_CAT";

        //when
        CategoryDTO found = categoryService.addOrUpdateCategory(category.getCategoryId(), newCategoryName);

        //then
        assertEquals(found.categoryName, category.getCategoryName());
    }
}
