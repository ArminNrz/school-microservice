package com.school.academic.dto.unit.teacher;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitTeacherDTO extends UnitTeacherBaseDTO {
    private Long id;
    private Long teacherId;
    private BigDecimal teacherPointSum;
}
