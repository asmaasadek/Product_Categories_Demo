package com.assignment.demo.product;

import com.assignment.demo.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findAll/{categoryId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProductDTO>> getAllProducts(@PathVariable("categoryId") int categoryId) {
        List<ProductDTO> products = productService.getAllProducts(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ProductDTO> addProduct(@RequestParam(value = "product-name") String productName) {
        ProductDTO productDTO = productService.addProduct(productName);
        return new ResponseEntity(productDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<ProductDTO> updateProduct(@RequestParam(value = "product-id") int productId,
                                             @RequestParam(value = "product-name") String productName) {

        ProductDTO productDTO = productService.addOrUpdateProduct(productId, productName);
        return new ResponseEntity(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public void deleteProduct(@RequestParam(value = "product-id") int productId) {
        productService.deleteProduct(productId);
    }


}
