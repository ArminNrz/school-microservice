package com.school.academic.dto.lesson;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LessonBaseDTO implements Serializable {

    @NotEmpty
    private String name;
}
