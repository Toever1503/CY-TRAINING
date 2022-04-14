package com.service.impl;

import com.domain.BikeType;
import com.domain.dto.BikeTypeDTO;
import com.domain.model.BikeTypeMODEL;
import com.repository.BikeTypeRepository;
import com.service.IBikeTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BikeTypeServiceImpl implements IBikeTypeService {
    private final BikeTypeRepository bikeTypeRepository;

    public BikeTypeServiceImpl(BikeTypeRepository repository) {
        this.bikeTypeRepository = repository;
    }

    BikeType modelToEntity(BikeTypeMODEL model) {
        if (model == null) return null;
        return BikeType.builder()
                .id(model.getId())
                .typeName(model.getTypeName())
                .price(model.getPrice()).build();
    }

    BikeTypeMODEL toModel(BikeType bikeType) {
        if (bikeType == null) return null;
        return BikeTypeMODEL.builder()
                .id(bikeType.getId())
                .typeName(bikeType.getTypeName())
                .typeParent(bikeType.getTypeParent() != null ? bikeType.getTypeParent().getId() : null)
                .price(bikeType.getPrice()).build();
    }

    BikeTypeDTO toDto(BikeType bikeType) {
        if (bikeType == null) return null;
        return BikeTypeDTO.builder()
                .id(bikeType.getId())
                .typeName(bikeType.getTypeName())
                .typeParent(bikeType.getTypeParent())
                .price(bikeType.getPrice()).build();
    }

    @Override
    @Transactional
    public BikeTypeMODEL save(BikeTypeMODEL obj) {
        BikeType bikeType = modelToEntity(obj);
        bikeType.setTypeParent((obj.getTypeParent() == null || obj.getTypeParent() == 0L) ? null : bikeTypeRepository.findById(obj.getTypeParent()).orElse(null));
        return toModel(this.bikeTypeRepository.save(bikeType));
    }

    @Override
    @Transactional
    public BikeTypeDTO findById(Long id) {
        return toDto(this.bikeTypeRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Page<BikeTypeDTO> findAll(Pageable page) {
        return this.bikeTypeRepository.findAll(page).map(this::toDto);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        this.bikeTypeRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean changeToTicketType(Long id) {
        BikeType bikeType = this.bikeTypeRepository.findById(id).orElse(null);
        if (bikeType == null) return false;
        bikeType.setTypeParent(null);
        return this.bikeTypeRepository.save(bikeType) != null;
    }

    @Override
    public Page<BikeTypeDTO> findAllBikeTypes(Pageable page) {
        return this.bikeTypeRepository.findAllBikeTypes(page).map(this::toDto);
    }

    @Override
    public Page<BikeTypeDTO> findAllTickets(Pageable page) {
        return this.bikeTypeRepository.findAllTickets(page).map(this::toDto);
    }
}
