package com.repository;

import com.entity.AuthorityEnitty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEnitty, Integer> {
}
