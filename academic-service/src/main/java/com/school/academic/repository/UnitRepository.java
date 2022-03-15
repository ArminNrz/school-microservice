package com.school.academic.repository;

import com.school.academic.domain.Unit;
import com.school.academic.repository.data.TeacherUnitPointSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
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

    @Query("select u " +
            "from UnitStudent us " +
            "join us.unit u " +
            "join us.student s " +
            "where s.id=:studentId")
    List<Unit> findAllUnitsByStudentId(@Param("studentId") Long studentId);

    @Query("select sum(u.point) " +
            "from UnitStudent us " +
            "join us.unit u " +
            "join us.student s " +
            "where s.id=:studentId")
    BigDecimal findSumOfPointsByStudentId(@Param("studentId") Long studentId);
}

