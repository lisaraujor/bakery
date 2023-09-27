package com.bakery.backend.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bakery.backend.application.dtos.BakeryDTO;
import com.bakery.backend.domain.entities.Bakery;
import com.bakery.backend.domain.entities.Stock;
import com.bakery.backend.domain.exceptions.NotFoundException;
import com.bakery.backend.infrastructure.repositories.DbBakeryRepository;

@Service
public class BakeryService {
    private final DbBakeryRepository dbBakeryRepository;
    private final StockService stockService;

    @Autowired
    public BakeryService(DbBakeryRepository dbBakeryRepository, StockService stockService) {
        this.dbBakeryRepository = dbBakeryRepository;
        this.stockService = stockService;
    }

    public List<BakeryDTO> getAll(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Bakery> bakerys = dbBakeryRepository.findAll(sort);

        return bakerys.stream()
        .map(bakery -> new ModelMapper().map(bakery, BakeryDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<BakeryDTO> getById(Long id){
        Optional<Bakery> bakeryOpt = dbBakeryRepository.findById(id);
        
        if(bakeryOpt.isEmpty()){
            throw new NotFoundException("Bakery with id " + id + " not found.");
        }

        BakeryDTO dto = new ModelMapper().map(bakeryOpt.get(), BakeryDTO.class);
        return Optional.of(dto);
    }

    public BakeryDTO create(BakeryDTO bakeryDto){
        bakeryDto.setId(null);

        Stock stock = stockService.create();
        Bakery bakery = new Bakery(bakeryDto.getName(), bakeryDto.getLocation(), stock);
        bakery = dbBakeryRepository.save(bakery);

        bakeryDto.setId(bakery.getId());

        return bakeryDto;
    }

    public BakeryDTO update(Long id, BakeryDTO bakeryDto){
        
        Optional<Bakery> bakeryOpt = dbBakeryRepository.findById(id);
        
        if (bakeryOpt.isPresent()){
            Bakery bakery = bakeryOpt.get();

            if (!bakeryDto.getName().isEmpty())
                bakery.setName(bakeryDto.getName());
            if (!bakeryDto.getLocation().isEmpty())
                bakery.setLocation(bakeryDto.getLocation());

            dbBakeryRepository.save(bakery);

            ModelMapper mapper = new ModelMapper();
            return mapper.map(bakery, BakeryDTO.class);
        }
        else {
            throw new NotFoundException("Bakery with id " + id + " not found.");
        }
    }

    public void delete(Long id) {

        Optional<Bakery> bakeryOpt = dbBakeryRepository.findById(id);

        if(bakeryOpt.isEmpty()){
            throw new NotFoundException("Bakery with id " + id + " not found.");
        }

        dbBakeryRepository.deleteById(id);
    }
}
