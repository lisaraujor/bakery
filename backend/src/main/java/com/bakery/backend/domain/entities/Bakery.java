package com.bakery.backend.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "bakery")
public class Bakery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "bakery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockProduct> stockProducts = new ArrayList<>();

    public Bakery() {
        // Empty constructor
    }

    public Bakery(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity > 0) {
            for (StockProduct stockProduct : stockProducts){
                if (stockProduct.getProduct().equals(product))
                    stockProduct.setQuantity((stockProduct.getQuantity() + quantity));
            }
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (quantity > 0) {
            for (StockProduct stockProduct : stockProducts){
                if (stockProduct.getProduct().equals(product))
                    stockProduct.setQuantity(Math.max(stockProduct.getQuantity() - quantity, 0));
            }
        }
    }
    
    public boolean isProductAvailable(Product product) {
        for (StockProduct stockProduct : stockProducts){
            if (stockProduct.getProduct().equals(product) && stockProduct.getQuantity() > 0)
                return true;                
        }
        return false;
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

    public List<StockProduct> getStockProducts() {
        return stockProducts;
    }
    public void setStock(List<StockProduct> stockProducts) {
        this.stockProducts = stockProducts;
    }
 
}
