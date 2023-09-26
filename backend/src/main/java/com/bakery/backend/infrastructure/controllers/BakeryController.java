package com.bakery.backend.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import com.bakery.backend.application.dtos.BakeryDTO;
import com.bakery.backend.application.dtos.ProductDTO;
import com.bakery.backend.application.services.BakeryService;

@RestController
@RequestMapping("/api/bakery")
public class BakeryController {
    
    private final BakeryService bakeryService;

    @Autowired
    public BakeryController(BakeryService bakeryService) {
        this.bakeryService = bakeryService;
    }

    @GetMapping
    public ResponseEntity<List<BakeryDTO>> getAll() {
        List<BakeryDTO> bakerys = bakeryService.getAll();
        return ResponseEntity.ok(bakerys);
    }
    
    @PostMapping
    public ResponseEntity<BakeryDTO> create(@RequestBody BakeryDTO bakery) {
        System.out.println("Esta chegando aqui!!");
        BakeryDTO newBakery = bakeryService.create(bakery);
        return new ResponseEntity<>(newBakery, HttpStatus.CREATED);
    }

    @PutMapping("/addProduct/{idBakery}")
    public ResponseEntity<ProductDTO> addProduct(@PathVariable Integer idBakery, @RequestBody Integer idProduct, Integer quantity) {
        ProductDTO addedProduct = bakeryService.addProductToStock(idBakery, idProduct, quantity);
        return ResponseEntity.ok(addedProduct);
    }

    // APAGAR UMA PADARIA
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> delete(@PathVariable Long id) {
    //     bakeryService.delete(id);
    //     return ResponseEntity.noContent().build();
    // }
}
