package com.school.clients.academic;

import com.school.baseLayer.dto.StudentBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDTO extends StudentBaseDTO {
    private Long id;

    @Override
    public String toString() {
        return "StudentDTO {" +
                "id=" + id + ", " +
                super.toString() +
                '}';
    }
}
