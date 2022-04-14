package com.service;

import com.domain.dto.BikeTicketDTO;
import com.domain.model.BikeTicketMODEL;
import com.domain.model.BikeTicketSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBikeTicketService extends IBaseService<BikeTicketDTO, BikeTicketMODEL, Long> {
    Page<BikeTicketDTO> search(BikeTicketSearchModel search, Pageable pageable);
}
