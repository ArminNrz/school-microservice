package com.school.academic.dto.unit.student;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UnitStudentDetail {
    private String teacherName;
    private String lessonName;
    private BigDecimal point;
}
