package com.school.academic.service.jms.producer;

import com.school.academic.domain.Student;
import com.school.academic.mapper.StudentMapper;
import com.school.amqp.RabbitMQMessageProducer;
import com.school.amqp.dto.student.StudentNotificationDTO;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.school.amqp.dto.enumartion.NotificationType.SMS;
import static com.school.amqp.dto.enumartion.StudentNotificationType.END_TERM;
import static com.school.amqp.dto.enumartion.StudentNotificationType.PAYMENT;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentNotificationProducer {

    private final RabbitMQMessageProducer producer;
    private final StudentMapper mapper;

    @Value("${rabbitMQ.exchanges.internal}")
    private String exchange;

    @Value("${rabbitMQ.routing-keys.internal-student-notification}")
    private String studentNotifRoutingKey;

    public void produceEndTermNotification(Student student, BigDecimal pointSum) {
        log.debug("Try to produce end term notification for Student: {}", student);
        if (hasNotStudentPhoneNumber(student)) return;

        StudentNotificationDTO event = mapper.toEvent(student, pointSum);
        event.setType(SMS);
        event.setStudentNotificationType(END_TERM);
        producer.publish(event, exchange, studentNotifRoutingKey);
    }

    public void producePaymentNotification(Student student, BigDecimal pointSum, String invoiceCode) {
        log.debug("Try to produce payment notification for Student: {}", student);
        if (hasNotStudentPhoneNumber(student)) return;

        StudentPaymentNotificationDTO event = mapper.toPaymentEvent(student, pointSum, invoiceCode);
        event.setType(SMS);
        event.setStudentNotificationType(PAYMENT);
        producer.publish(event, exchange, studentNotifRoutingKey);
    }

    private boolean hasNotStudentPhoneNumber(Student student) {
        if (student.getPhoneNumber() == null) {
            log.warn("Student: {}, has no phone number", student.getId());
            return true;
        }
        return false;
    }
}
