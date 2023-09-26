package com.bakery.backend.domain.repositories;

import java.util.List;

import com.bakery.backend.domain.entities.Product;

public interface BakeryRepository {
    Product getById(Long id);

    List<Product> getAll();

    Product addProduct(Product product, int quantity);

    Product removeProduct(Product product, int quantity);

    void isProductAvailable(Product product, int quantity);
}
