package com.repository;

import com.domain.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Serializable> {
    Page<Orders> findAll(Pageable page);

    Page<Orders> findAllByCreatedUser(Long userId, Pageable page);
}
