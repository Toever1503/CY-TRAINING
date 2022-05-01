package com.repository;

import com.domain.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("select c from Character c join Videoandchar as vc on vc.id.chars = c.id where vc.id.video = ?1")
    Page<Character> findAllCharactersByVideoId(Long id, Pageable pageable);

    @Query("select c from Character c join Videoandchar as vc on vc.id.chars = c.id where c.name like %?1% and vc.id.video = ?2")
    Page<Character> searchByAndVideoId(String name, Long id, Pageable pageable);

    Page<Character> findAllByNameLike(String name, Pageable pageable);
}