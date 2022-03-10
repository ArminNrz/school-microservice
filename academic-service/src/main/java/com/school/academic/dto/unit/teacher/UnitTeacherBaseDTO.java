package com.school.academic.dto.unit.teacher;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UnitTeacherBaseDTO implements Serializable {

    @NotNull
    private Long lessonId;

    @NotNull
    private BigDecimal point;
}
