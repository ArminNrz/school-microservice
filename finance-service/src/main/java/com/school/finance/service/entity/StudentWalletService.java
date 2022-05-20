package com.school.finance.service.entity;

import com.school.clients.finance.dto.StudentWalletResponse;
import com.school.finance.domain.StudentWallet;
import com.school.finance.mapper.StudentWalletMapper;
import com.school.finance.repository.StudentWalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentWalletService {

    private final StudentWalletRepository repository ;
    private final StudentWalletMapper mapper ;

    public Optional<StudentWallet> getByStudentId(Long studentId) {
        log.debug("Request to get Wallet by Student Id : {}" , studentId);
        Optional<StudentWallet> studentWallet = repository.findByStudentId(studentId) ;

        return studentWallet ;
    }

    public StudentWalletResponse create(Long studentId) {
        log.debug("request to create a wallet" , studentId);
        Optional<StudentWallet> walletOptional = this.getByStudentId(studentId) ;
        if(walletOptional.isPresent())
            throw Problem.valueOf(Status.FORBIDDEN , "The student has a wallet") ;
        StudentWallet wallet = mapper.toEntity(studentId) ;
        log.debug("Wallet Created successfully");
        return mapper.toResponse(repository.save(wallet)) ;

    }
}
