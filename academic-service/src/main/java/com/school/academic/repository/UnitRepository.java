package com.school.academic.repository;

import com.school.academic.domain.Unit;
import com.school.academic.repository.data.LessonUnitPointSum;
import com.school.academic.repository.data.StudentUnitPointSum;
import com.school.academic.repository.data.TeacherUnitPointSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query(
            value = "SELECT sum(U.point) AS Sum " +
                    "FROM unit as U " +
                    "WHERE U.teacher_id =:teacherId AND U.is_active = true"
            , nativeQuery = true)
    TeacherUnitPointSum countTeacherUnitPoint(@Param("teacherId") Long teacherId);

    Optional<Unit> findByTeacherIdAndLessonId(Long teacherId, Long lessonId);

    List<Unit> findAllByIdIsIn(List<Long> ids);

    @Query(
            value = "SELECT sum(U.point) AS Sum " +
                    "FROM unit as U " +
                    "WHERE U.lesson_id =:lessonId AND U.is_active = true"
            , nativeQuery = true)
    LessonUnitPointSum countLessonUnitPoint(@Param("lessonId") Long lessonId);


    @Query(value = "FROM Unit U where U.lessonId = :lessonId")
    List<Unit> getUnitsByLessonId(@Param("lessonId") Long lessonId);


    @Query(
            value = "SELECT sum(U.point) AS Sum " +
                    "FROM unit as U " +
                    "WHERE U.student_id =:studentId AND U.is_active = true"
            , nativeQuery = true)
    StudentUnitPointSum countStudentUnitPoint(@Param("studentId") Long StudentId);


}
