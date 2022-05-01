package com.repository;

import com.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t join Videoandtag vt on t.id = vt.id.tag where vt.id.video = ?1")
    Page<Tag> findAllTagsByVideoId(Long id, Pageable pageable);

    @Query("select t from Tag t join Videoandtag vt on t.id = vt.id.tag where vt.id.video = ?2 and t.name like %?1%")
    Page<Tag> searchByAndVideoId(String search, Long id, Pageable pageable);

    Page<Tag> findAllByNameLike(String search, Pageable pageable);
}