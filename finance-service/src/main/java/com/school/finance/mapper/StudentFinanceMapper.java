package com.school.finance.mapper;

import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.domain.StudentFinance;
import com.school.finance.dto.student.StudentFinanceDTO;
import com.school.finance.dto.student.payment.StudentPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentFinanceMapper {

    private final StudentPaymentMapper studentPaymentMapper;

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

        List<StudentPaymentDTO> studentPaymentDTOList = studentFinance.getStudentPayments().stream()
                .map(studentPaymentMapper::toDTO)
                .collect(Collectors.toList());
        dto.setPaymentDetails(studentPaymentDTOList);

        return dto;
    }

    public StudentPaymentNotificationDTO toEvent(StudentFinance entity) {
        StudentPaymentNotificationDTO event = new StudentPaymentNotificationDTO();

        event.setStudentId(entity.getStudentId());
        event.setPointSum(entity.getPointSum());
        event.setInvoiceCode(entity.getId());

        return event;
    }
}
