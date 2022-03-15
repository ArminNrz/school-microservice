package com.school.academic.dto.unit.student;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode
@Data
public class UnitDetailsDTO {
    private String teacherName;
    private String lessonName;
    private BigDecimal point;
}
