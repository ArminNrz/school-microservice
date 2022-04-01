package com.school.finance.mapper;


import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.StudentPaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentPaymentMapper {

    public StudentPayment toStudentPayment (StudentPaymentDTO studentPaymentDTO) {

        StudentPayment studentPayment = new StudentPayment() ;
        studentPayment.setStudentId(studentPaymentDTO.getStudentId());
        studentPayment.setAmount(studentPaymentDTO.getAmount());
        return studentPayment ;
    }

    public StudentPaymentDTO toDTO (StudentPayment studentPayment) {
        StudentPaymentDTO studentPaymentDTO = new StudentPaymentDTO() ;
        studentPaymentDTO.setStudentId(studentPayment.getStudentId());
        studentPaymentDTO.setId(studentPayment.getId());
        studentPaymentDTO.setAmount(studentPayment.getAmount());
        return studentPaymentDTO ;
    }
}
