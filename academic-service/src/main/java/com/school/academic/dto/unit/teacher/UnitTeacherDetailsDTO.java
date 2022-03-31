package com.school.academic.dto.unit.teacher;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UnitTeacherDetailsDTO {
    private Long unitId;
    private String LessonName;
    private BigDecimal point;
}
