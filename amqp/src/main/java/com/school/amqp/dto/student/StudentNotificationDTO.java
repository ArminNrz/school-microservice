package com.school.amqp.dto.student;

import com.school.amqp.dto.BaseNotificationDTO;
import com.school.amqp.dto.enumartion.StudentNotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentNotificationDTO extends BaseNotificationDTO {
    private String firstName;
    private String lastName;
    private BigDecimal pointSum;
    private String phoneNumber;
    private Long studentId;
    private StudentNotificationType studentNotificationType;
}
