package com.school.amqp.dto;

import com.school.amqp.dto.enumartion.NotificationType;
import lombok.Data;

@Data
public class BaseNotificationDTO {
    private NotificationType type;
}
