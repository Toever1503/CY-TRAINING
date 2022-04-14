package com.example.practice_2022_04_08.repository;

import com.example.practice_2022_04_08.domain.AvailSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface AvailableSubjectRepository extends JpaRepository<AvailSubject, Long> {

    Optional<AvailSubject> findByStartDateAndStartTime(Date startDate, String startTime);


//    @Query("SELECT a FROM AvailSubject a " +
//            "left join RegisteredSubject r on a.id = r.availSubject.id " +
//            "left join Student s on r.student.id = s.id where s.id != ?1" )
    @Query(value = "select * from avail_subjects\n" +
            "where id not in(\n" +
            "\tselect rs.avail_subject_id from registered_subjects as rs\n" +
            "    where rs.student_id = :stu_id and rs.status = 1)", nativeQuery = true)
    Page<AvailSubject> findTotalUnRegisteredAvailSubjectForStudent(@Param("stu_id") Long stuId , Pageable page);

    @Query("SELECT a FROM AvailSubject a " +
            "left join RegisteredSubject r on a.id = r.availSubject.id " +
            "left join Student s on r.student.id = s.id where s.id = ?1" )
    Page<AvailSubject> findTotalRegisteredAvailSubjectForStudent(Long stuId, Pageable page);

}
