package com.school.finance.service.entity;

import com.school.clients.finance.dto.ChargeWalletRequest;
import com.school.clients.finance.dto.ChargeWalletResponse;
import com.school.clients.finance.dto.StudentWalletResponse;
import com.school.finance.domain.StudentWallet;
import com.school.finance.mapper.StudentWalletMapper;
import com.school.finance.repository.StudentWalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentWalletService {

    private final StudentWalletRepository repository ;
    private final StudentWalletMapper mapper ;

    public Optional<StudentWallet> getByStudentId(Long studentId) {
        log.debug("Request to get Wallet by Student Id : {}" , studentId);
        return repository.findByStudentId(studentId) ;
    }

    public StudentWalletResponse create(Long studentId) {
        log.debug("request to create a wallet for student : {} " , studentId);
        Optional<StudentWallet> walletOptional = this.getByStudentId(studentId) ;
        if(walletOptional.isPresent())
            throw Problem.valueOf(Status.FORBIDDEN , "The student has a wallet") ;
        StudentWallet wallet = mapper.toEntity(studentId) ;
        log.debug("Wallet Created successfully");
        return mapper.toResponse(repository.save(wallet)) ;
    }
    public StudentWallet charge(String walletId , BigDecimal amount) {
        log.debug("Request to update wallet balance : {}" , walletId);
        Optional<StudentWallet> wallet = repository.findById(walletId) ;
        if(wallet.isEmpty())
            throw Problem.valueOf(Status.NOT_FOUND,"The wallet not found ...");
        wallet.get().setBalance(wallet.get().getBalance().add(amount));
        log.debug("balance updated successfully ...");
        return repository.save(wallet.get()) ;
    }

}
