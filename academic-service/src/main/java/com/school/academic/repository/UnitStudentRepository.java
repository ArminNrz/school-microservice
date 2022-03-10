package com.school.academic.repository;

import com.school.academic.domain.UnitStudent;
import com.school.academic.repository.data.StudentUnitPointSum;
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
}
