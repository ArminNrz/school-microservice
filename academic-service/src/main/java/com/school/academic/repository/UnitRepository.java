package com.school.academic.repository;

import com.school.academic.domain.Unit;
import com.school.academic.repository.data.TeacherUnitPointSum;
import com.school.academic.repository.data.UnitTeacherData;
import org.hibernate.annotations.Parameter;
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

    List<Unit> findAllByLessonId(Long lessonId);

    @Query(
            value = "SELECT U.id AS id, L.name AS LessonName, U.point AS Point " +
                    "FROM unit as U " +
                    "INNER JOIN lesson L on U.lesson_id = L.id " +
                    "INNER JOIN teacher T on U.teacher_id = T.id " +
                    "WHERE U.teacher_id =:teacherId",
            nativeQuery = true
    )
    List<UnitTeacherData> findUnitByTeacherId(@Param("teacherId") Long teacherId);
}
