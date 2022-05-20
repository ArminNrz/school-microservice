package com.school.academic.service.higlevel.student.handler;


import com.school.academic.domain.Student;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.thirdparty.FinanceClientService;
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

}
