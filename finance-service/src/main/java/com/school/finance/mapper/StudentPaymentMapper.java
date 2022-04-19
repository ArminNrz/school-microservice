package com.school.finance.mapper;

import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.StudentPaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentPaymentMapper {

    public StudentPaymentDTO toDTO(StudentPayment entity) {
        StudentPaymentDTO dto = new StudentPaymentDTO();

        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setCreated(entity.getCreated());

        return dto;
    }

}
