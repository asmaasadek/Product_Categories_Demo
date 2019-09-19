package com.assignment.demo.Product;

import com.assignment.demo.dto.ProductDTO;
import com.assignment.demo.product.Product;
import com.assignment.demo.product.ProductRepository;
import com.assignment.demo.product.ProductService;
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
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    private Product product;


    @Before
    public void setUp() {
        product = new Product("PRO");
        product.setProductId(1);

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(product);
    }

    @Test
    public void whenUpdateProduct_thenUpdatedShouldReturn() {
        //given
        String newProductName = "NEW_PRO";

        //when
        ProductDTO found = productService.addOrUpdateProduct(product.getProductId(), newProductName);

        //then
        assertEquals(found.productName, product.getProductName());
    }

}
