package com.school.finance.dto.student;

import com.school.baseLayer.dto.StudentBaseDTO;
import com.school.finance.dto.student.payment.StudentPaymentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPayedDTO extends StudentBaseDTO {
    private List<StudentPaymentDTO> paymentDetails;
}
