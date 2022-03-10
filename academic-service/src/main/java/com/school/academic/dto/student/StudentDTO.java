package com.school.academic.dto.student;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDTO extends StudentBaseDTO {
    private Long id;
}
