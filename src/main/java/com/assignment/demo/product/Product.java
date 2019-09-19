package com.assignment.demo.product;

import com.assignment.demo.category.Category;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name")
    private String productName;

    public Product() {
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST},
            mappedBy = "products"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Category> categories = new HashSet<>();
}
