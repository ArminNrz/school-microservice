package com.school.academic.service.higlevel.student;

import com.school.academic.domain.Student;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.jms.producer.StudentNotificationProducer;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentNotificationManager {

    private final StudentService studentService;
    private final StudentNotificationProducer studentNotificationProducer;

    public void sendPayFactorNotification(StudentPaymentNotificationDTO notificationDTO) {
        log.debug("Request to send student payment factor notification, notificationDTO: {}", notificationDTO);

        Student student = studentService.findById(notificationDTO.getStudentId());
        if(student == null) {
            log.error("Student with id: {}, is not exist, Can not send notification to this Student", notificationDTO.getStudentId());
            return;
        }

        studentNotificationProducer.producePaymentNotification(student, notificationDTO.getPointSum(), notificationDTO.getInvoiceCode());
    }
}
