package com.bakery.backend.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bakery.backend.domain.entities.StockProduct;

@Repository
public interface DbStockProductRepository extends JpaRepository<StockProduct, Long> {

}
