package com.school.finance.repository;

import com.school.finance.domain.StudentPayment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentPaymentRepository extends MongoRepository<StudentPayment,String> {

    List<StudentPayment> getAllByStudentFinanceId(String invoiceCode) ;
}
