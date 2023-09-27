package com.bakery.backend.application.dtos;

import java.util.List;

public class BakeryDTO {
    private Long id;

    private String name;

    private String location;

    private List<StockProductDTO> stockProducts;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public List<StockProductDTO> getStockProducts() {
        return stockProducts;
    }
    public void setStockProducts(List<StockProductDTO> stockProducts) {
        this.stockProducts = stockProducts;
    }
}