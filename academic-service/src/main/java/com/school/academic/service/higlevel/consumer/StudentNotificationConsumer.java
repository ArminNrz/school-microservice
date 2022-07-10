package com.school.academic.service.higlevel.consumer;

import com.school.academic.service.higlevel.student.StudentNotificationManager;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentNotificationConsumer {

    private final StudentNotificationManager notificationManager;

    @RabbitListener(queues = "${rabbitMQ.queues.student-notification-pre}")
    public void consumeStudentNotificationPre(StudentPaymentNotificationDTO notificationDTO) {
        log.info("Consumed {}, from student notification pre queue", notificationDTO);
        notificationManager.sendPayFactorNotification(notificationDTO);
    }
}
