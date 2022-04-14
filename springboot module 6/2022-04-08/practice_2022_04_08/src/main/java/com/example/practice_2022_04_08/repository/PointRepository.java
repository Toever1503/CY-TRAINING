package com.example.practice_2022_04_08.repository;

import com.example.practice_2022_04_08.domain.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("select p from Point p join RegisteredSubject rs on rs.id = p.registeredSubject.id join Subject s on s.id = rs.availSubject.subject.id where s.id = ?1")
//    @Query(value = "select * from points as p\n" +
//            "right join students as stu on stu.id = p.student_id\n" +
//            "left join avail_subjects as avs on avs.id = p.availsubject_id\n" +
//            "left join subjects as s on s.id = avs.subject_id where s.id = :subjectId;", nativeQuery = true)
    Page<Point> getTotalPointOfStudentsBySubject(Long subjectId, Pageable page);

    @Query("select p from Point p join RegisteredSubject rs on rs.id = p.registeredSubject.id join Subject s on s.id = rs.availSubject.subject.id where rs.student.id = ?1")
    Page<Point> getTotalPointOfStudents(Long stuId, Pageable page);


    @Query("select p from Point p join RegisteredSubject rs on rs.id = p.registeredSubject.id join Subject s on s.id = rs.availSubject.subject.id where rs.student.id = ?1 and p.pointType = 'CuoiKy'")
    List<Point> getTotalFinalPointOfStudents(Long stuId);

    @Transactional
    void deleteByRegisteredSubjectId(Long id);
}
