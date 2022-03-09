package com.school.academic.dto.teacher;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDTO extends TeacherBaseDTO {
    private Long id;
}
