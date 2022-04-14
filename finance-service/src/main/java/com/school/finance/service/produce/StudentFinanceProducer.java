package com.school.finance.service.produce;

import com.school.amqp.RabbitMQMessageProducer;
import com.school.amqp.dto.enumartion.StudentNotificationType;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import com.school.finance.domain.StudentFinance;
import com.school.finance.mapper.StudentFinanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.school.amqp.dto.enumartion.NotificationType.SMS;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentFinanceProducer {

    private final RabbitMQMessageProducer producer;
    private final StudentFinanceMapper mapper;

    @Value("${rabbitMQ.exchanges.internal}")
    private String exchange;

    @Value("${rabbitMQ.routing-keys.internal-student-notification-pre}")
    private String studentNotifPreRoutingKey;

    public void produceToStudentNotificationPreQueue(StudentFinance studentFinance) {
        log.debug("Try to produce notification for StudentFinance: {}", studentFinance);

        StudentPaymentNotificationDTO event = mapper.toEvent(studentFinance);
        event.setType(SMS);
        event.setStudentNotificationType(StudentNotificationType.PAYMENT);
        producer.publish(event, exchange, studentNotifPreRoutingKey);
    }
}
