package com.school.notification.service.comsume;

import com.school.notification.service.notification.StudentNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.school.amqp.dto.student.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentNotificationConsumer {

    private final StudentNotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.student-notification}")
    public void consumeStudentNotification(StudentPaymentNotificationDTO notificationDTO) {
        log.info("Consumed {}, from student notification queue", notificationDTO);
        notificationService.send(notificationDTO);
    }
}
