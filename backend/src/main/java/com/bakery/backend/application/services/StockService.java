package com.bakery.backend.application.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakery.backend.application.dtos.ProductDTO;
import com.bakery.backend.application.dtos.StockProductDTO;
import com.bakery.backend.domain.entities.Bakery;
import com.bakery.backend.domain.entities.Product;
import com.bakery.backend.domain.entities.StockProduct;
import com.bakery.backend.domain.exceptions.InvalidQuantityException;
import com.bakery.backend.domain.exceptions.NotFoundException;
import com.bakery.backend.infrastructure.repositories.DbBakeryRepository;
import com.bakery.backend.infrastructure.repositories.DbProductRepository;
import com.bakery.backend.infrastructure.repositories.DbStockProductRepository;

@Service
public class StockService {
    private final DbBakeryRepository dbBakeryRepository;
    private final DbStockProductRepository dbStockProductRepository;
    private final DbProductRepository dbProductRepository;

    @Autowired
    public StockService(DbBakeryRepository dbBakeryRepository, DbStockProductRepository dbStockProductRepository, DbProductRepository dbProductRepository) {
        this.dbBakeryRepository = dbBakeryRepository;
        this.dbStockProductRepository = dbStockProductRepository;
        this.dbProductRepository = dbProductRepository;
    }

    public ProductDTO addProduct(Long idBakery, Long idProduct, Integer quantity){
        if (quantity < 1)
            throw new InvalidQuantityException("The quantity to be added cannot be less than 1.");        
        
        Optional<Product> productOpt = dbProductRepository.findById(idProduct);
        Optional<Bakery> bakeryOpt = dbBakeryRepository.findById(idBakery);
        boolean existingProduct = false;

        if (productOpt.isPresent() && bakeryOpt.isPresent()){
            Product product = productOpt.get();
            Bakery bakery = bakeryOpt.get();

            List<StockProduct> stockProducts = bakery.getStock();
            for (StockProduct stockProduct : stockProducts){
                if (stockProduct.getProduct().getId() == idProduct){
                    stockProduct.setQuantity((stockProduct.getQuantity() + quantity));
                    existingProduct = true;
                    dbStockProductRepository.save(stockProduct);
                }
            }
            if (!existingProduct){
                StockProduct stockProductNew = new StockProduct(product, quantity, bakery);
                stockProducts.add(stockProductNew);
                dbStockProductRepository.save(stockProductNew);
            }

            dbBakeryRepository.save(bakery);

            ModelMapper mapper = new ModelMapper();
            return mapper.map(product, ProductDTO.class);
        }
        else {
            throw new NotFoundException("Product with id " + idProduct + " not found. It was not possible add to Stock.");
        }
    }

    public ProductDTO removeProduct(Long idBakery, Long idProduct, Integer quantity){
        if (quantity < 1)
            throw new InvalidQuantityException("The quantity to be removed cannot be less than 1.");        
        
        Optional<Product> productOpt = dbProductRepository.findById(idProduct);
        Optional<Bakery> bakeryOpt = dbBakeryRepository.findById(idBakery);

        if (productOpt.isPresent() && bakeryOpt.isPresent()){
            Product product = productOpt.get();
            Bakery bakery = bakeryOpt.get();

            List<StockProduct> stockProducts = bakery.getStock();
            for (StockProduct stockProduct : stockProducts){
                if (stockProduct.getProduct().getId() == idProduct){
                    stockProduct.setQuantity(Math.max(stockProduct.getQuantity() - quantity, 0));
                    dbStockProductRepository.save(stockProduct);
                }
            }

            dbBakeryRepository.save(bakery);
            ModelMapper mapper = new ModelMapper();
            return mapper.map(product, ProductDTO.class);
        }
        else {
            throw new NotFoundException("Product with id " + idProduct + " not found. It was not possible add to Stock.");
        }
    }

    public StockProductDTO isProductAvailable(Long idBakery, Long idProduct) {
        Optional<Product> productOpt = dbProductRepository.findById(idProduct);
        Optional<Bakery> bakeryOpt = dbBakeryRepository.findById(idBakery);

        if (productOpt.isPresent() && bakeryOpt.isPresent()){
            Product product = productOpt.get();
            Bakery bakery = bakeryOpt.get();

            List<StockProduct> stockProducts = bakery.getStock();
            for (StockProduct stockProduct : stockProducts){
                if (stockProduct.getProduct().equals(product) && stockProduct.getQuantity() > 0){
                    ModelMapper mapper = new ModelMapper();
                    return mapper.map(stockProduct, StockProductDTO.class);    
                }            
            }
        }
        throw new NotFoundException("Product with id " + idProduct + " not found.");
    }
}
