package com.school.finance.repository;


import com.school.finance.domain.StudentWallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentWalletRepository extends MongoRepository<StudentWallet , String> {
    Optional<StudentWallet> findByStudentId(Long studentId) ;
}
