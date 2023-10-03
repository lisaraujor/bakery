package com.bakery.backend.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bakery.backend.application.dtos.BakeryDTO;
import com.bakery.backend.application.dtos.ProductDTO;
import com.bakery.backend.application.dtos.StockProductDTO;
import com.bakery.backend.application.services.BakeryService;
import com.bakery.backend.application.services.StockService;
import com.bakery.backend.infrastructure.models.ProductToStockRequest;

@RestController
@RequestMapping("/api/bakery")
public class BakeryController {
    
    private final BakeryService bakeryService;
    private final StockService stockService;

    @Autowired
    public BakeryController(BakeryService bakeryService, StockService stockService) {
        this.bakeryService = bakeryService;
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<BakeryDTO>> getAll() {
        List<BakeryDTO> bakerys = bakeryService.getAll();
        return ResponseEntity.ok(bakerys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BakeryDTO>> getById(@PathVariable Long id) {
        Optional<BakeryDTO> bakery = bakeryService.getById(id);
        return ResponseEntity.ok(bakery);
    }
    
    @PostMapping
    public ResponseEntity<BakeryDTO> create(@RequestBody BakeryDTO bakery) {
        BakeryDTO newBakery = bakeryService.create(bakery);
        return new ResponseEntity<>(newBakery, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BakeryDTO> update(@PathVariable Long id, @RequestBody BakeryDTO bakery) {
        BakeryDTO updatedBakery = bakeryService.update(id, bakery);
        return ResponseEntity.ok(updatedBakery);
    }

    @PutMapping("/addProduct/{idBakery}")
    public ResponseEntity<ProductDTO> addProduct(@PathVariable Long idBakery, @RequestBody ProductToStockRequest addProductToStockRequest) {
        ProductDTO addedProduct = stockService.addProduct(idBakery, addProductToStockRequest.getIdProduct(), addProductToStockRequest.getQuantity());
        return ResponseEntity.ok(addedProduct);
    }

    @PutMapping("/removeProduct/{idBakery}")
    public ResponseEntity<ProductDTO> removeProduct(@PathVariable Long idBakery, @RequestBody ProductToStockRequest removeProductToStockRequest) {
        ProductDTO removedProduct = stockService.removeProduct(idBakery, removeProductToStockRequest.getIdProduct(), removeProductToStockRequest.getQuantity());
        return ResponseEntity.ok(removedProduct);
    }

    @GetMapping("/isProductAvailable/{idBakery}")
    public ResponseEntity<StockProductDTO> isProductAvailable(@PathVariable Long idBakery, @RequestBody ProductDTO product) {
        StockProductDTO stockProduct = stockService.isProductAvailable(idBakery, product.getId());
        return ResponseEntity.ok(stockProduct);
    }

}
