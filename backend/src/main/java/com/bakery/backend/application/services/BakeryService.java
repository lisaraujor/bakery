package com.bakery.backend.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bakery.backend.application.dtos.BakeryDTO;
import com.bakery.backend.application.dtos.ProductDTO;
import com.bakery.backend.domain.entities.Bakery;
import com.bakery.backend.domain.entities.Product;
import com.bakery.backend.domain.entities.Stock;
import com.bakery.backend.domain.exceptions.ProductNotFoundException;
import com.bakery.backend.infrastructure.repositories.DbBakeryRepository;
import com.bakery.backend.infrastructure.repositories.DbProductRepository;

@Service
public class BakeryService {
    private final DbProductRepository dbProductRepository;
    private final DbBakeryRepository dbBakeryRepository;

    @Autowired
    public BakeryService(DbProductRepository dbProductRepository, DbBakeryRepository dbBakeryRepository) {
        this.dbProductRepository = dbProductRepository;
        this.dbBakeryRepository = dbBakeryRepository;
    }

    // public Optional<ProductDTO> getById(Long id){
    //     Optional<Product> product = dbProductRepository.findById(id);
        
    //     if(product.isEmpty()){
    //         throw new ProductNotFoundException("Product with id " + id + " not found.");
    //     }

    //     ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
    //     return Optional.of(dto);
    // }

    public List<BakeryDTO> getAll(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id"); // Ordenar por ID em ordem crescente
        List<Bakery> bakerys = dbBakeryRepository.findAll(sort);

        return bakerys.stream()
        .map(bakery -> new ModelMapper().map(bakery, BakeryDTO.class))
        .collect(Collectors.toList());
    }

    public BakeryDTO create(BakeryDTO bakeryDto){
        bakeryDto.setId(null);
        
        Bakery bakery = new Bakery(bakeryDto.getName(), bakeryDto.getLocation());
        Stock stock = new Stock(bakery);

        bakery.setStock(stock);
        bakery = dbBakeryRepository.save(bakery);

        bakeryDto.setId(bakery.getId());

        return bakeryDto;
    }

    public ProductDTO addProductToStock(Long idBakery, Long idProduct, int quantity){

        Optional<Product> existingProductOpt = dbProductRepository.findById(idProduct);
        Optional<Bakery> existingBakeryOpt = dbBakeryRepository.findById(idBakery);
        
        if (existingProductOpt.isPresent()){
            Product product = existingProductOpt.get();
            Bakery bakery = existingBakeryOpt.get();
            bakery.addProduct(product, quantity);

            dbProductRepository.save(product);

            ModelMapper mapper = new ModelMapper();
            return mapper.map(product, ProductDTO.class);
        }
        else {
            throw new ProductNotFoundException("Product with id " + idProduct + " not found. It was not possible add to Stock.");
        }
    }

    // public void delete(Long id) {

    //     Optional<Product> product = dbProductRepository.findById(id);

    //     if(product.isEmpty()){
    //         throw new ProductNotFoundException("Product with id " + id + " not found.");
    //     }

    //     dbProductRepository.deleteById(id);
    // }
}
