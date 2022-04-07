package com.school.finance.mapper;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.domain.StudentFinance;
import com.school.finance.dto.StudentFinanceDTO;
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

    public StudentFactorResponse ToFactorResponse (StudentFinance studentFinance) {
        StudentFactorResponse factor = new StudentFactorResponse() ;
        factor.setInvoiceCode(studentFinance.getId());
        factor.setPointSum(studentFinance.getPointSum());
        factor.setCost(studentFinance.getCost());
        return factor ;
    }

    public StudentFinanceDTO toDTO(StudentFinance studentFinance) {
        StudentFinanceDTO dto = new StudentFinanceDTO();

        dto.setId(studentFinance.getId());
        dto.setStudentId(studentFinance.getStudentId());
        dto.setIsPaid(studentFinance.getIsPaid());
        dto.setCost(studentFinance.getCost());
        dto.setCreated(studentFinance.getCreated());
        dto.setUpdated(studentFinance.getUpdated());

        return dto;
    }
}
