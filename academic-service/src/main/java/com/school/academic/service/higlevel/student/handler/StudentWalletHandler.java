package com.school.academic.service.higlevel.student.handler;


import com.school.academic.domain.Student;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.jms.producer.StudentNotificationProducer;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.ChargeWalletRequest;
import com.school.clients.finance.dto.ChargeWalletResponse;
import com.school.clients.finance.dto.StudentWalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.transaction.Transactional;


@Component
@Slf4j
@RequiredArgsConstructor
public class StudentWalletHandler {
    private final FinanceClientService financeService;
    private final StudentService studentService ;
    private final StudentNotificationProducer studentNotificationProducer;

    @Transactional
    public StudentWalletResponse create(Long studentId) {
        log.debug("Request to create wallet for student : {}  " , studentId);
        Student student = studentService.findById(studentId) ;
        if(student == null)
            throw Problem.valueOf(Status.NOT_FOUND ,"Student not found ") ;
        StudentWalletResponse response = financeService.createWallet(studentId) ;
        if(response != null) {
            student.setWalletId(response.getWalletId());
            studentService.update(student);
        }
        log.debug("Wallet created successfully for student Id : {} . The walletId is : {} " , studentId , response.getWalletId());
        return response ;
    }

    @Transactional
    public ChargeWalletResponse chargeWallet(ChargeWalletRequest request) {
        log.debug("Request to charge the wallet : {}" , request);
        if(studentService.findById(request.getStudentId()) == null)
            throw Problem.valueOf(Status.NOT_FOUND , "The student not found with id") ;
        ChargeWalletResponse response = financeService.chargeWallet(request) ;
        studentNotificationProducer.produceTransactionNotification(request.getAmount() , response);
        return response ;
    }
}
