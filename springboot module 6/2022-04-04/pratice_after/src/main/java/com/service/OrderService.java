package com.service;

import com.service.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDto findById(Long id);

    OrderDto save(OrderDto obj);

    boolean deleteById(Long id);

    Page<OrderDto> findAll(Pageable page);

    Page<OrderDto> findAllByUser(Long userId, Pageable page);
}
