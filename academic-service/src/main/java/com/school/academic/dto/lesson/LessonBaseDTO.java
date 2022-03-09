package com.school.academic.dto.lesson;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LessonBaseDTO implements Serializable {

    @NotNull
    private String name;
}
