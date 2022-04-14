package com.school.finance.repository;

import com.school.finance.domain.StudentFinance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentFinanceRepository extends MongoRepository<StudentFinance, String> {
    Optional<StudentFinance> findByStudentId(Long studentId) ;
    List<StudentFinance> findByIsPaid(Boolean isPaid) ;
}
