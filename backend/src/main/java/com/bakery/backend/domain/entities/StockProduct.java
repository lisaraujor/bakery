package com.bakery.backend.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stockProduct")
public class StockProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;

    public StockProduct() {
        
    }
    public StockProduct(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Metodos devem buscar pelo id do produto
    // public void addProduct(Product product, Integer quantity) {
    //     if (quantity > 0) {
    //         Integer currentQuantity = products.getOrDefault(product, 0);
    //         products.put(product, currentQuantity + quantity);
    //     }
    // }

    // public void removeProduct(Product product, Integer quantity) {
    //     // Modify the check, if a quantity value greater than stock is entered, throw an exception
    //     if (quantity > 0) {
    //         Integer currentQuantity = products.getOrDefault(product, 0);
    //         Integer newQuantity = Math.max(currentQuantity - quantity, 0);
    //         if (newQuantity == 0) {
    //             products.remove(product);
    //         } else {
    //             products.put(product, newQuantity);
    //         }
    //     }
    // }

    // public boolean checkStockAvailability(Product product, int quantity) {
    //     int currentQuantity = products.getOrDefault(product, 0);
    //     return currentQuantity >= quantity;
    // }

    // public Map<Product, Integer> getProducts() {
    //     return products;
    // }
    // public void setProducts(Map<Product, Integer> products) {
    //     this.products = products;
    // }
}
