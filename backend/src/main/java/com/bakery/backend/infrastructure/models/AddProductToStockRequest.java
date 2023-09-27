package com.bakery.backend.infrastructure.models;

public class AddProductToStockRequest {
    public int idProduct;
    public int quantity;
    
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
