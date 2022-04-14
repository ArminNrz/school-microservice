package com.school.amqp.dto.student;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentPaymentNotificationDTO extends StudentNotificationDTO {
    private String invoiceCode;
}
