package com.school.finance.repository;


import com.school.finance.domain.WalletLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletLogRepository extends MongoRepository<WalletLog,String> {

}
