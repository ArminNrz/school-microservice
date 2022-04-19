package com.school.academic.mapper;

import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentCreateDTO;
import com.school.clients.academic.StudentDTO;
import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.student.StudentFactorDTO;
import com.school.amqp.dto.student.StudentNotificationDTO;
import com.school.amqp.dto.student.StudentPaymentNotificationDTO;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StudentMapper {

    public Student toEntity(StudentCreateDTO dto) {
        Student entity = new Student();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNationalCode(dto.getNationalCode());
        entity.setAccessUnitRegistration(true);
        return entity;
    }

    public StudentDTO toDTO(Student entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setNationalCode(entity.getNationalCode());
        return dto;
    }

    public StudentDetailsDTO toDetailsDTO(Student entity) {
        StudentDetailsDTO dto = new StudentDetailsDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setNationalCode(entity.getNationalCode());
        return dto;
    }

    public StudentFinanceRegisterRequest toStudentFinanceRequest(Long studentId, BigDecimal pointSum) {
        StudentFinanceRegisterRequest request = new StudentFinanceRegisterRequest();
        request.setStudentId(studentId);
        request.setPointSum(pointSum);
        return request;
    }

    public StudentFactorDTO toFactorDTO (Student student) {
        StudentFactorDTO factorDTO = new StudentFactorDTO() ;
        factorDTO.setFirstName(student.getFirstName());
        factorDTO.setLastName(student.getLastName());
        factorDTO.setNationalCode(student.getNationalCode());

        return factorDTO ;
    }

    public StudentNotificationDTO toEvent(Student student, BigDecimal pointSum) {
        StudentNotificationDTO notificationDTO = new StudentNotificationDTO();
        notificationDTO.setFirstName(student.getFirstName());
        notificationDTO.setLastName(student.getLastName());
        notificationDTO.setPhoneNumber(student.getPhoneNumber());
        notificationDTO.setPointSum(pointSum);
        return notificationDTO;
    }

    public StudentPaymentNotificationDTO toPaymentEvent(Student student, BigDecimal pointSum, String invoiceCode) {
        StudentPaymentNotificationDTO event = new StudentPaymentNotificationDTO();

        event.setFirstName(student.getFirstName());
        event.setLastName(student.getLastName());
        event.setPhoneNumber(student.getPhoneNumber());
        event.setPointSum(pointSum);
        event.setInvoiceCode(invoiceCode);

        return event;
    }
}
