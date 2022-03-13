package com.school.academic.repository;

import com.school.academic.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {



    @Query("From Student st where st.nationalCode =:nationalCode")
    Student getStudentByNationalId(Long NationalCode) ;
}
