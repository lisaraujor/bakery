package com.bakery.backend.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bakery.backend.domain.entities.Product;

@Repository
public interface DbProductRepository extends JpaRepository<Product, Long> {

}

