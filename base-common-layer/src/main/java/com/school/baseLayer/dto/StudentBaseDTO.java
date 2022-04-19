package com.school.baseLayer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public abstract class StudentBaseDTO implements Serializable {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Long nationalCode;

    public String toString() {
        return "firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", nationalCode=" + this.getNationalCode();
    }
}
