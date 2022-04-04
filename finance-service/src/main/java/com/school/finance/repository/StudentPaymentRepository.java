package com.school.finance.repository;

import com.school.finance.domain.StudentPayment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentPaymentRepository extends MongoRepository<StudentPayment,String> {
}
