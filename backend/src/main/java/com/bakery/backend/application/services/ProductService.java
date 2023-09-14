package com.bakery.backend.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakery.backend.domain.entities.Product;
import com.bakery.backend.domain.repositories.ProductRepository;
import com.bakery.backend.infrastructure.repositories.DbProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final DbProductRepository dbProductRepository;

    @Autowired
    public ProductService(DbProductRepository dbProductRepository) {
        this.dbProductRepository = dbProductRepository;
    }

    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = this.dbProductRepository.findById(id);
        return product;
    }

    // public List<Product> getAllProducts() {
    //     // Implemente a lógica para buscar todos os produtos
    // }

    public Product createProduct(Product product) {
        Product created_product = this.dbProductRepository.save(product);
        return created_product;
    }

    // public Product updateProduct(Long id, Product product) {
    //     // Implemente a lógica para atualizar um produto existente
    // }

    // public void deleteProduct(Long id) {
    //     // Implemente a lógica para excluir um produto
    // }
}