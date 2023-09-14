package com.bakery.backend.domain.repositories;

import java.util.List;

import com.bakery.backend.domain.entities.Product;

public interface ProductRepository {
    Product getById(Long id);

    List<Product> getAll();

    Product create(Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}

