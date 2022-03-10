package com.school.finance.repository;

import com.school.finance.domain.StudentFinance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentFinanceRepository extends MongoRepository<StudentFinance, String> {
}
