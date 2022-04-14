package com.service;

import com.domain.dto.BikeTypeDTO;
import com.domain.model.BikeTypeMODEL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBikeTypeService extends IBaseService<BikeTypeDTO, BikeTypeMODEL, Long>{

    boolean changeToTicketType(Long id);
    Page<BikeTypeDTO> findAllBikeTypes(Pageable page);
    Page<BikeTypeDTO> findAllTickets(Pageable page);
}
