package com.school.academic.service.jms.producer;

import com.school.academic.domain.Student;
import com.school.academic.mapper.StudentMapper;
import com.school.amqp.RabbitMQMessageProducer;
import com.school.amqp.dto.student.StudentNotificationDTO;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import com.school.amqp.dto.student.StudentTransactionDTO;
import com.school.clients.finance.dto.ChargeWalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.Watchable;

import static com.school.amqp.dto.enumartion.NotificationType.SMS;
import static com.school.amqp.dto.enumartion.StudentNotificationType.*;

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

    @Value("${rabbitMQ.routing-keys.internal-student-notification-trans}")
    private String studentNotifTransRoutingKey;



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

    public void produceTransactionNotification(BigDecimal amount , ChargeWalletResponse response) {
        log.debug("try to produce Transaction for student : {}" , response.getStudentId());
        StudentTransactionDTO event = mapper.toTransactionEvent(amount , response) ;
        event.setType(SMS);
        event.setStudentNotificationType(WALLET);
        producer.publish(event,exchange,studentNotifTransRoutingKey);

    }

    private boolean hasNotStudentPhoneNumber(Student student) {
        if (student.getPhoneNumber() == null) {
            log.warn("Student: {}, has no phone number", student.getId());
            return true;
        }
        return false;
    }
}
