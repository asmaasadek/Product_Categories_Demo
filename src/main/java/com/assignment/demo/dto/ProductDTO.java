package com.assignment.demo.dto;

import java.io.Serializable;
import java.util.Set;

public class ProductDTO implements Serializable {
    public ProductDTO(int productId, String productName, Set<CategoryDTO> categories) {
        this.productId = productId;
        this.productName = productName;
        this.categories = categories;
    }

    public int productId;
    public String productName;
    public Set<CategoryDTO> categories;
}
