package com.school.academic.dto.unit.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitStudentRegistrationDTO extends UnitStudentBaseDTO {

    @JsonIgnore
    private Long studentId;
}
