package com.school.academic.dto.student;

import com.school.academic.dto.unit.student.UnitDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDetailDTO extends StudentBaseDTO {
    private BigDecimal pointSum;
    private List<UnitDetailsDTO> studentUnits;
}
