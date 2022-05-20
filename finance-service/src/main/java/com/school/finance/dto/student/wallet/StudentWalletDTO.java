package com.school.finance.dto.student.wallet ;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentWalletDTO extends StudentWalletBaseDTO{
    private String id ;
}
