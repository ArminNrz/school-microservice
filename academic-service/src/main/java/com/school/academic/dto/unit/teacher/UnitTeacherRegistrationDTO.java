package com.school.academic.dto.unit.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitTeacherRegistrationDTO extends UnitTeacherBaseDTO {

    @JsonIgnore
    private Long teacherId;
}
