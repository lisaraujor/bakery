package com.bakery.backend.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.backend.domain.entities.Product;

public interface DbProductRepository extends JpaRepository<Product, Long> {
    // Adicione métodos de consulta personalizados, se necessário
}

