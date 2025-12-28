package com.product.controller;

import com.product.entity.Product;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = service.saveProduct(product);
        return ResponseEntity.status(CREATED).body(savedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Product product = service.findProductById(id);
        if (product != null) {
            return ResponseEntity.status(OK).body(product);
        } else {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = service.getProducts();
        return ResponseEntity.status(OK).body(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String response = service.deleteProduct(id);
        return ResponseEntity.status(OK).body(response);
    }

    @PutMapping("/update")
   public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = service.updateProduct(product);
        if (updatedProduct != null) {
            return ResponseEntity.status(OK).body(updatedProduct);
        } else {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }
}

