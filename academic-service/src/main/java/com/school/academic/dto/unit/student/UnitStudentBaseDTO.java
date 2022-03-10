package com.school.academic.dto.unit.student;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UnitStudentBaseDTO implements Serializable {

    @NotNull
    private Long unitId;
}
