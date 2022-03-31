package com.school.academic.dto.student;

import com.school.academic.dto.unit.student.UnitStudentDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDetailsDTO extends StudentBaseDTO {
    private List<UnitStudentDetail> unitDetails;
    private BigDecimal unitPointSum;
}
