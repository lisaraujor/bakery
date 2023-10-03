package com.bakery.backend.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stockProduct")
public class StockProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;

    public StockProduct() {
        this.product = new Product();
        this.quantity = 0;
    }
    public StockProduct(Product product, Integer quantity, Bakery bakery) {
        this.product = product;
        this.quantity = quantity;
        this.bakery = bakery;
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

    public Bakery getBakery() {
        return bakery;
    }
    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }
}
