package com.school.finance.service.entity;


import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.StudentPaymentDTO;
import com.school.finance.mapper.StudentPaymentMapper;
import com.school.finance.repository.StudentPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentPaymentService {

    private final StudentPaymentRepository paymentRepository ;
    private final StudentPaymentMapper paymentMapper;

    public StudentPayment savePayment(StudentPaymentDTO studentPaymentDTO) {
        log.debug("Request to save payment : {}", studentPaymentDTO);

        if (studentPaymentDTO != null)
            return paymentRepository.save(paymentMapper.toStudentPayment(studentPaymentDTO)) ;
            return null ;
    }
}
