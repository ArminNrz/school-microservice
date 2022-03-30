package com.school.academic.repository;

import com.school.academic.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByNationalCode(Long nationalCode);
}
