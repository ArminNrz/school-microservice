package com.school.academic.dto.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitRegistrationDTO extends UnitBaseDTO {

    @JsonIgnore
    private Long teacherId;
}
