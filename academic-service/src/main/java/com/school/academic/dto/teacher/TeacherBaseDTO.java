package com.school.academic.dto.teacher;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class TeacherBaseDTO implements Serializable {

    @NotEmpty
    private String name;

    @NotNull
    private Long nationalCode;
}
