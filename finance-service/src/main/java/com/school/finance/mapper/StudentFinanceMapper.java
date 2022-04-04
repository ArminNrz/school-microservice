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

    public StudentFinanceDTO toDTO (StudentFinance studentFinance) {
        StudentFinanceDTO studentFinanceDTO = new StudentFinanceDTO() ;
        studentFinanceDTO.setStudentId(studentFinance.getStudentId());
        studentFinanceDTO.setId(studentFinance.getId());
        studentFinanceDTO.setCost(studentFinance.getCost());
        studentFinanceDTO.setIsPaid(studentFinance.getIsPaid());
        studentFinanceDTO.setDateTime(studentFinance.getDateTime());
        studentFinanceDTO.setUpdateTime(studentFinance.getUpdateTime());
        return studentFinanceDTO ;
    }

    public StudentFinance toFinanceEntity (StudentFinanceDTO studentFinanceDTO) {
        StudentFinance studentFinance = new StudentFinance() ;
        studentFinance.setStudentId(studentFinanceDTO.getStudentId());
        studentFinance.setId(studentFinanceDTO.getId());
        studentFinance.setIsPaid(studentFinanceDTO.getIsPaid());
        studentFinance.setCost(studentFinanceDTO.getCost());
        studentFinance.setDateTime(studentFinanceDTO.getDateTime());
        studentFinanceDTO.setUpdateTime(studentFinanceDTO.getUpdateTime());
        return studentFinance ;

    }
}
