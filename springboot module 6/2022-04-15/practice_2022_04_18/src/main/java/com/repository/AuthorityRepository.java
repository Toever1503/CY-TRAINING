package com.repository;

import com.domain.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Page<Authority> findAllByRoleNameLike(String q, Pageable page);

    Set<Authority> findAllByIdIn(Set<Integer> ids);
}
