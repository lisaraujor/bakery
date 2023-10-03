package com.bakery.backend.infrastructure.models;

public class ProductToStockRequest {
    public Long idProduct;
    public Integer quantity;
    
    public Long getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }   
}
