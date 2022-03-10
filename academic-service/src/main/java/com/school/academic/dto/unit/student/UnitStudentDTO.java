package com.school.academic.dto.unit.student;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitStudentDTO extends UnitStudentBaseDTO {

    private Long studentId;

    private BigDecimal point;

    private BigDecimal studentPointSum;
}
