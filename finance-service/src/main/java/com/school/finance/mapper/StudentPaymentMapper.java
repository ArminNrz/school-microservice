package com.school.finance.mapper;

import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.StudentPaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentPaymentMapper {

    public StudentPayment toEntity (StudentPaymentDTO studentPaymentDTO) {

        StudentPayment studentPayment = new StudentPayment() ;
        studentPayment.setAmount(studentPaymentDTO.getAmount());
        studentPayment.setStudentFinanceId(studentPaymentDTO.getStudentFinanceId());
        studentPayment.setInitialCost(studentPaymentDTO.getInitialCost());
        studentPayment.setNewCost(studentPaymentDTO.getNewCost());
        studentPayment.setCreateDateTime(studentPaymentDTO.getCreateDateTime());

        return studentPayment ;

    }

    public StudentPaymentDTO toDTO (StudentPayment studentPayment) {

        StudentPaymentDTO studentPaymentDTO = new StudentPaymentDTO() ;
        studentPaymentDTO.setId(studentPayment.getId());
        studentPaymentDTO.setAmount(studentPayment.getAmount());
        studentPaymentDTO.setInitialCost(studentPayment.getInitialCost());
        studentPaymentDTO.setCreateDateTime(studentPayment.getCreateDateTime());
        studentPaymentDTO.setNewCost(studentPayment.getNewCost());

        return studentPaymentDTO ;
    }

}
