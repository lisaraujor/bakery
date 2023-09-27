package com.bakery.backend.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockProduct> stockProducts;

    public Stock() {

    }
    public Stock(List<StockProduct> stockProducts) {
        this.stockProducts = stockProducts;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<StockProduct> getStockProducts() {
        return stockProducts;
    }
    public void setStockProducts(List<StockProduct> stockProducts) {
        this.stockProducts = stockProducts;
    }
}
