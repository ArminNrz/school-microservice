package com.school.finance.mapper;

import com.school.clients.finance.dto.StudentWalletResponse;
import com.school.finance.domain.StudentWallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StudentWalletMapper {

    public StudentWalletResponse toResponse(StudentWallet wallet) {
        StudentWalletResponse response = new StudentWalletResponse() ;
        response.setWalletId(wallet.getId());
        response.setStudentId(wallet.getStudentId());
        return response ;
    }

    public StudentWallet toEntity(Long studentId) {
        StudentWallet wallet = new StudentWallet( );
        wallet.setStudentId(studentId);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setActive(true);
        return wallet ;
    }
}
