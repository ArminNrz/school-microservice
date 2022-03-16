package com.school.academic.repository;

import com.school.academic.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByNationalCode(Long nationalCode);
}
