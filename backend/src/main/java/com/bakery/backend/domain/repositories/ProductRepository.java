package com.bakery.backend.domain.repositories;

import java.util.List;

import com.bakery.backend.domain.entities.Product;

public interface ProductRepository {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}

