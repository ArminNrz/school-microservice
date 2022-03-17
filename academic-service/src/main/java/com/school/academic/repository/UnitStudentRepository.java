package com.school.academic.repository;

import com.school.academic.domain.UnitStudent;
import com.school.academic.repository.data.StudentUnitPointSum;
import com.school.academic.repository.data.UnitDetailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitStudentRepository extends JpaRepository<UnitStudent, Long> {

    @Query(
            value = "SELECT sum(U.point) AS Sum " +
                    "FROM unit_student AS US INNER JOIN unit AS U on US.unit_id = U.id " +
                    "WHERE US.student_id =:studentId AND U.is_active = true",
            nativeQuery = true
    )
    StudentUnitPointSum getStudentUnitPointSum(@Param("studentId") Long studentId);

    List<UnitStudent> findAllByStudentId(Long studentId);

    @Query(
            value = "SELECT T.name AS TeacherName, L.name AS LessonName, U.point AS Point " +
                    "FROM unit_student as US " +
                        "INNER JOIN unit U on US.unit_id = U.id " +
                        "INNER JOIN lesson L on U.lesson_id = L.id " +
                        "INNER JOIN teacher T on U.teacher_id = T.id " +
                    "WHERE US.student_id =:studentId",
            nativeQuery = true
    )
    List<UnitDetailData> findUnitByStudentId(@Param("studentId") Long studentId);
}
