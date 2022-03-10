package com.school.finance.mapper;

import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.domain.StudentFinance;
import org.springframework.stereotype.Component;

@Component
public class StudentFinanceMapper {

    public StudentFinance toEntity(StudentFinanceRegisterRequest request) {
        StudentFinance entity = new StudentFinance();
        entity.setStudentId(request.getStudentId());
        entity.setPointSum(request.getPointSum());
        entity.setIsPaid(false);
        return entity;
    }

    public StudentFinanceRegisterResponse toResponse(StudentFinance entity) {
        StudentFinanceRegisterResponse response = new StudentFinanceRegisterResponse();
        response.setStudentId(entity.getStudentId());
        response.setInvoiceCode(entity.getId());
        return response;
    }
}
