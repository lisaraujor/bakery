package com.bakery.backend.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bakery.backend.application.services.ProductService;
import com.bakery.backend.domain.entities.Product;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // @GetMapping
    // public ResponseEntity<List<Product>> getAllProducts() {
    //     List<Product> products = productService.getAllProducts();
    //     return ResponseEntity.ok(products);
    // }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    //     Product updatedProduct = productService.updateProduct(id, product);
    //     return ResponseEntity.ok(updatedProduct);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    //     productService.deleteProduct(id);
    //     return ResponseEntity.noContent().build();
    // }
}

