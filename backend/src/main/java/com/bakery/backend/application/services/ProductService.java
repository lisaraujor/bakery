package com.bakery.backend.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.bakery.backend.application.dtos.ProductDTO;
import com.bakery.backend.domain.entities.Product;
import com.bakery.backend.infrastructure.repositories.DbProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final DbProductRepository dbProductRepository;

    @Autowired
    public ProductService(DbProductRepository dbProductRepository) {
        this.dbProductRepository = dbProductRepository;
    }

    public Optional<ProductDTO> getById(Long id){
        Optional<Product> product = dbProductRepository.findById(id);
        
        // if(product.isEmpty()){
        //     throw new ResourceNotFoundException("Product with id: " + id + " not find");
        // }

        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }

    public List<ProductDTO> getAll(){
        List<Product> products = dbProductRepository.findAll();

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
        productDto.setId(id);
        
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDto, Product.class);

        dbProductRepository.save(product);

        return productDto;
    }

    // public void deleteProduct(Long id) {
    //     // Implemente a lógica para excluir um produto
    // }
}