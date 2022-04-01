package com.school.academic.dto.student;


import com.school.academic.dto.unit.student.UnitStudentDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentFactorDTO extends StudentBaseDTO {
    private List<UnitStudentDetail> unitList ;
    private BigDecimal pointSum ;
    private String invoiceCode  ;
    private BigDecimal cost ;
}
