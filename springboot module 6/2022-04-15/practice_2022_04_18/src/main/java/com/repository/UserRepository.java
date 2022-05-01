package com.repository;

import com.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username like %?1% or u.email like %?1% or u.phone like %?1% or u.fullName like %?1% or u.address like %?1%")
    Page<User> search(String q, Pageable page);
}
