package com.repository;

import com.domain.BikeTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface BikeTicketRepository extends JpaRepository<BikeTicket, Long> {

    @Query("select b from BikeTicket b where (?1 is not null and b.bikeCode like ?1) or (?2 is not null and b.bikeColor like ?2) or b.bikeType.id = ?3 or b.ticketType.id  = ?4 or " +
            "(?5 is not null and b.ticketType.price between ?5 and ?6) or" +
            "(?7 is not null and b.timeStart between ?7 and ?8)")
    Page<BikeTicket> searchTicket(String s, String s1, Long bikeType, Long ticketType, Float minPrice, Float maxPrice, Date date, Date date1, Pageable page);
}
