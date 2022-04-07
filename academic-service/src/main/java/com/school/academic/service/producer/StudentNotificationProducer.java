package com.school.academic.service.producer;

import com.school.academic.domain.Student;
import com.school.academic.mapper.StudentMapper;
import com.school.amqp.RabbitMQMessageProducer;
import com.school.amqp.dto.enumartion.NotificationType;
import com.school.amqp.dto.student.StudentNotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentNotificationProducer {

    private final RabbitMQMessageProducer producer;
    private final StudentMapper mapper;

    @Value("${academic.rabbitMQ.exchanges.internal}")
    private String exchange;

    @Value("${academic.rabbitMQ.routing-keys.internal-student-notification}")
    private String studentNotifRoutingKey;

    public void produce(Student student, BigDecimal pointSum) {
        log.debug("Try to produce notification for Student: {}", student);
        if (student.getPhoneNumber() == null) {
            log.warn("Student: {}, has no phone number", student.getId());
            return;
        }

        StudentNotificationDTO event = mapper.toEvent(student, pointSum);
        event.setType(NotificationType.SMS);
        producer.publish(event, exchange, studentNotifRoutingKey);
    }
}
