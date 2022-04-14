package com.service.impl;

import com.domain.BikeTicket;
import com.domain.BikeType;
import com.domain.dto.BikeTicketDTO;
import com.domain.model.BikeTicketMODEL;
import com.domain.model.BikeTicketSearchModel;
import com.repository.BikeTicketRepository;
import com.repository.BikeTypeRepository;
import com.service.IBikeTicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class BikeTicketServiceImpl implements IBikeTicketService {

    private final BikeTicketRepository bikeTicketRepository;
    private final BikeTypeRepository bikeTypeRepository;

    public BikeTicketServiceImpl(BikeTicketRepository bikeTicketRepository, BikeTypeRepository bikeTypeRepository) {
        this.bikeTicketRepository = bikeTicketRepository;
        this.bikeTypeRepository = bikeTypeRepository;
    }

    BikeTicket toEntity(BikeTicketMODEL bikeType) {
        if (bikeType == null) return null;
        return BikeTicket.builder()
                .id(bikeType.getId())
                .bikeCode(bikeType.getBikeCode())
                .bikeColor(bikeType.getBikeColor()).build();
    }

    BikeTicketDTO toDTO(BikeTicket bikeTicket) {
        if (bikeTicket == null) return null;
        return BikeTicketDTO.builder()
                .id(bikeTicket.getId())
                .bikeCode(bikeTicket.getBikeCode())
                .bikeColor(bikeTicket.getBikeColor())
                .bikeType(bikeTicket.getBikeType())
                .ticketType(bikeTicket.getTicketType())
                .timeStart(bikeTicket.getTimeStart())
                .timeEnd(bikeTicket.getTimeEnd()).build();
    }

    BikeTicketMODEL toModel(BikeTicket bikeTicket) {
        if (bikeTicket == null) return null;
        return BikeTicketMODEL.builder()
                .id(bikeTicket.getId())
                .bikeCode(bikeTicket.getBikeCode())
                .bikeColor(bikeTicket.getBikeColor())
                .bikeType(bikeTicket.getBikeType() != null ? bikeTicket.getBikeType().getId() : null)
                .ticketType(bikeTicket.getTicketType() != null ? bikeTicket.getTicketType().getId() : null).build();
    }


    @Override
    @Transactional
    public BikeTicketMODEL save(BikeTicketMODEL obj) {
        BikeType bikeType = this.bikeTypeRepository.findById(obj.getBikeType()).orElse(null);
        BikeType ticketType = this.bikeTypeRepository.findByIdAndTypeParentIsNull(obj.getTicketType()).orElse(null);

        if (bikeType == null || ticketType == null) return null;
        BikeTicket bikeTicket = toEntity(obj);
        if (obj.getId() == null) bikeTicket.setTimeStart(Calendar.getInstance().getTime());
        else {
            BikeTicket oldBikeTicket = this.bikeTicketRepository.findById(obj.getId()).orElse(null);
            if (oldBikeTicket == null) return null;
            bikeTicket.setTimeStart(oldBikeTicket.getTimeStart());
            bikeTicket.setTimeEnd(oldBikeTicket.getTimeEnd());
        }
        bikeTicket.setTicketType(ticketType);
        bikeTicket.setBikeType(bikeType);
        bikeTicket = this.bikeTicketRepository.save(bikeTicket);

        return toModel(bikeTicket);
    }

    @Override
    @Transactional
    public BikeTicketDTO findById(Long id) {
        return toDTO(this.bikeTicketRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public Page<BikeTicketDTO> findAll(Pageable page) {
        return this.bikeTicketRepository.findAll(page).map(this::toDTO);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        this.bikeTicketRepository.deleteById(id);
        return true;
    }


    @Override
    public Page<BikeTicketDTO> search(BikeTicketSearchModel search, Pageable pageable) {
        return this.bikeTicketRepository.searchTicket(
                search.getBikeCode() != null ? "%".concat(search.getBikeCode().concat("%")) : null,
                search.getBikeColor() != null ? "%".concat(search.getBikeColor().concat("%")) : null,
                search.getBikeType(),
                search.getTicketType(),
                search.getMinPrice(),
                search.getMaxPrice(),
                search.getTimeStart(),
                search.getTimeEnd(), pageable).map(this::toDTO);
    }
}
