package com.repository;

import com.domain.BikeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BikeTypeRepository extends JpaRepository<BikeType, Long> {
    @Query(value = "select * from bike_types\n" +
            "where type_parent is null", nativeQuery = true)
    Page<BikeType> findAllTickets(Pageable page);

    @Query(value = "select * from bike_types\n" +
            "where type_parent is not null", nativeQuery = true)
    Page<BikeType> findAllBikeTypes(Pageable page);

    Optional<BikeType> findByIdAndTypeParentIsNull(Long id);

    Optional<BikeType> findByTypeParent(Long typeParent);
}
