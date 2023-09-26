package com.bakery.backend.domain.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bakery")
public class Bakery implements Serializable {
    
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String location;

    @OneToOne(mappedBy = "bakery", cascade = CascadeType.ALL)
    private Stock stock;

    public Bakery(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addProduct(Product product, int quantity) {
        stock.addProduct(product, quantity);
    }

    public void removeProduct(Product product, int quantity) {
        stock.removeProduct(product, quantity);
    }
    
    public boolean isProductAvailable(Product product, int quantity) {
        return stock.checkStockAvailability(product, quantity);
    }

    public Long getId() {
        return id;
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
