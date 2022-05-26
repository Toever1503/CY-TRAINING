package com.repository;

import com.entities.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.title like %?1% or n.content like %?1%")
    Page<Note> search(String q, Pageable page);
}
