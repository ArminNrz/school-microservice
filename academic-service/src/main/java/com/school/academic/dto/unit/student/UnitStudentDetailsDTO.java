package com.school.academic.dto.unit.student;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitStudentDetailsDTO extends UnitStudentBaseDTO {

    private String teacherName;
    private String lessonName;
    private BigDecimal point;
}
