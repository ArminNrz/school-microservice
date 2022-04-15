package com.school.finance.mapper;

import com.school.clients.finance.dto.StudentPaymentsReportResponse;
import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.StudentPaymentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentPaymentMapper {

    public StudentPayment toEntity (StudentPaymentDTO studentPaymentDTO) {

        StudentPayment studentPayment = new StudentPayment() ;
        studentPayment.setAmount(studentPaymentDTO.getAmount());
        studentPayment.setStudentFinanceId(studentPaymentDTO.getStudentFinanceId());
        studentPayment.setInitialCost(studentPaymentDTO.getInitialCost());
        studentPayment.setNewCost(studentPaymentDTO.getNewCost());

        return studentPayment ;

    }

    public StudentPaymentDTO toDTO (StudentPayment studentPayment) {

        StudentPaymentDTO studentPaymentDTO = new StudentPaymentDTO() ;
        studentPaymentDTO.setId(studentPayment.getId());
        studentPaymentDTO.setAmount(studentPayment.getAmount());
        studentPaymentDTO.setInitialCost(studentPayment.getInitialCost());
        studentPaymentDTO.setNewCost(studentPayment.getNewCost());

        return studentPaymentDTO ;
    }

    public StudentPaymentsReportResponse toReportResponse (StudentPaymentDTO paymentDTO) {
        StudentPaymentsReportResponse reportResponse = new StudentPaymentsReportResponse() ;
        reportResponse.setPaymentId(paymentDTO.getId());
        reportResponse.setAmount(paymentDTO.getAmount());
        reportResponse.setInitialCost(paymentDTO.getInitialCost());
        reportResponse.setCreateDateTime(paymentDTO.getCreateDateTime());
        reportResponse.setInvoiceCode(paymentDTO.getStudentFinanceId());
        return reportResponse ;
    }

    public List<StudentPaymentsReportResponse> toReportResponseList(List<StudentPaymentDTO> paymentDTOS) {

        return paymentDTOS.stream().map(this::toReportResponse).collect(Collectors.toList());


    }

}
