package com.school.amqp.dto.student;

import com.school.amqp.dto.BaseNotificationDTO;
import com.school.amqp.dto.enumartion.StudentNotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true )
public class StudentTransactionDTO extends BaseNotificationDTO {
    private Long studentId ;
    private BigDecimal amount ;
    private String TransactionId ;
    private String WalletId ;
    private BigDecimal currentBalance ;
    private StudentNotificationType studentNotificationType;
}
