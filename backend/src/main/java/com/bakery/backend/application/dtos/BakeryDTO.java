package com.bakery.backend.application.dtos;

import com.bakery.backend.domain.entities.Stock;

public class BakeryDTO {
    private Long id;

    private String name;

    private String location;

    private Stock stock;

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

    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }
}