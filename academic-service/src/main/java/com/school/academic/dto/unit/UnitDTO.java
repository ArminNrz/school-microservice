package com.school.academic.dto.unit;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitDTO extends UnitBaseDTO {
    private Long id;
    private Long teacherId;
    private BigDecimal teacherPointSum;
}
