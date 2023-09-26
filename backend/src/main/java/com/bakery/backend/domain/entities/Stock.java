package com.bakery.backend.domain.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {
    
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;
    
    @ElementCollection
    @CollectionTable(name = "stock_product_mapping", joinColumns = @JoinColumn(name = "stock_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

    public Stock(Bakery bakery) {
        this.bakery = bakery;
        this.products = new HashMap<>();
    }

    // Metodos devem buscar pelo id do produto
    public void addProduct(Product product, Integer quantity) {
        if (quantity > 0) {
            Integer currentQuantity = products.getOrDefault(product, 0);
            products.put(product, currentQuantity + quantity);
        }
    }

    public void removeProduct(Product product, Integer quantity) {
        // Modify the check, if a quantity value greater than stock is entered, throw an exception
        if (quantity > 0) {
            Integer currentQuantity = products.getOrDefault(product, 0);
            Integer newQuantity = Math.max(currentQuantity - quantity, 0);
            if (newQuantity == 0) {
                products.remove(product);
            } else {
                products.put(product, newQuantity);
            }
        }
    }

    public boolean checkStockAvailability(Product product, int quantity) {
        int currentQuantity = products.getOrDefault(product, 0);
        return currentQuantity >= quantity;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
