package com.product.service;

import com.product.entity.Product;
import com.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public Product findProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product updateProduct(Product product) {
        Product existingProduct = findProductById(product.getId());
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        }
        return null;
    }

    public String deleteProduct(Long id) {
        repository.deleteById(id);
        return "Product removed !! " + id;
    }
}
