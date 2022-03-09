package com.school.academic.repository;

import com.school.academic.domain.UnitStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitStudentRepository extends JpaRepository<UnitStudent, Long> {
}
