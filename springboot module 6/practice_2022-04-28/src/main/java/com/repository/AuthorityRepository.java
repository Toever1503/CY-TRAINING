package com.repository;

import com.entity.AuthorityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    Page<AuthorityEntity> findAllByAuthorityLike(String keyword, Pageable pageable);
    List<AuthorityEntity> findAllByIdIn(List<Integer> ids);
    AuthorityEntity findByAuthority(String authority);
}
