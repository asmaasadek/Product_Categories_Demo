package com.assignment.demo.product;

import com.assignment.demo.category.Category;
import com.assignment.demo.dto.ProductDTO;
import com.assignment.demo.dto.CategoryDTO;
import com.assignment.demo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(int categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        List<Product> products = productRepository.findAllByCategories(category);
        List<ProductDTO> productDTOList = new ArrayList();
        for (Product product : products) {
            productDTOList.add(new ProductDTO(product.getProductId(), product.getProductName(), CategoryDTO.mapDTO(product.getCategories())));
        }
        return productDTOList;
    }

    public ProductDTO addProduct(String productName) {
        Product product = new Product();
        product.setProductName(productName);
        Product addedProduct = productRepository.save(product);
        ProductDTO result = new ProductDTO(addedProduct.getProductId(), addedProduct.getProductName(), CategoryDTO.mapDTO(product.getCategories()));
        return result;
    }

    /**
     * @return updated project if it's already exist or new product if id not found
     */
    public ProductDTO addOrUpdateProduct(int productId, String productName) {
        Product product = updateProduct(productId, productName);
        return new ProductDTO(product.getProductId(), product.getProductName(), CategoryDTO.mapDTO(product.getCategories()));
    }

    public void deleteProduct(int productId) {
        if (!productRepository.findById(productId).isPresent())
            throw new ProductNotFoundException(productId);

        productRepository.deleteById(productId);
    }

    private Product updateProduct(int productId, String productName) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setProductName(productName);
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    return productRepository.save(new Product(productName));
                });
    }

}
