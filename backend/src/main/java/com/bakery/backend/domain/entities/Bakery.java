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
@Table(name = "bakery")
public class Bakery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "bakery", cascade = CascadeType.ALL)
    private List<StockProduct> stock;

    public Bakery() {

    }

    public Bakery(String name, String location, List<StockProduct> stock) {
        this.name = name;
        this.location = location;
        this.stock = stock;
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

    public List<StockProduct> getStock() {
        return stock;
    }
    public void setStock(List<StockProduct> stock) {
        this.stock = stock;
    }
}
