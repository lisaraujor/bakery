package com.bakery.backend.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.bakery.backend.application.dtos.ProductDTO;
import com.bakery.backend.domain.entities.Product;
import com.bakery.backend.domain.exceptions.ProductNotFoundException;
import com.bakery.backend.infrastructure.repositories.DbProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;

@Service
public class ProductService {
    private final DbProductRepository dbProductRepository;

    @Autowired
    public ProductService(DbProductRepository dbProductRepository) {
        this.dbProductRepository = dbProductRepository;
    }

    public Optional<ProductDTO> getById(Long id){
        Optional<Product> product = dbProductRepository.findById(id);
        
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }

        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }

    public List<ProductDTO> getAll(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id"); // Ordenar por ID em ordem crescente
        List<Product> products = dbProductRepository.findAll(sort);

        return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
    }

    public ProductDTO create(ProductDTO productDto){
        productDto.setId(null);

        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDto,Product.class);
        product = dbProductRepository.save(product);

        productDto.setId(product.getId());

        return productDto;
    }

    public ProductDTO update(Long id, ProductDTO productDto){
        
        Optional<Product> existingProductOpt = dbProductRepository.findById(id);
        
        if (existingProductOpt.isPresent()){
            Product product = existingProductOpt.get();

            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            dbProductRepository.save(product);

            ModelMapper mapper = new ModelMapper();
            return mapper.map(product, ProductDTO.class);
        }
        else {
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }
    }

    public void delete(Long id) {

        Optional<Product> product = dbProductRepository.findById(id);

        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found.");
        }

        dbProductRepository.deleteById(id);
    }
}