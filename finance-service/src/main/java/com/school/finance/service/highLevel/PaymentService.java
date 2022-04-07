package com.school.finance.service.highLevel;


import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.finance.domain.StudentFinance;
import com.school.finance.dto.StudentFinanceDTO;
import com.school.finance.dto.StudentPaymentDTO;
import com.school.finance.mapper.StudentFinanceMapper;
import com.school.finance.service.StudentFinanceService;
import com.school.finance.service.entity.StudentPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final StudentFinanceService studentFinanceService;
    private final StudentPaymentService studentPaymentService ;
    private final StudentFinanceMapper mapper ;

    @Transactional
    public StudentFinanceDTO payment(Long studentId , BigDecimal amount) {
        log.debug("Request to pay Factor with studentId {}, and amount : {} " , studentId , amount);
        StudentFinanceDTO studentFinanceDTO = studentFinanceService.getFactor(studentId) ;

        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            log.debug("Amount Equals Zero ... ");
            throw Problem.valueOf(Status.BAD_REQUEST , "The amount equals Zero !") ;
        }
        BigDecimal factorCost = studentFinanceDTO.getCost() ;
        if (amount.compareTo(factorCost) >= 0 ) {
            studentFinanceDTO.setCost(BigDecimal.ZERO);
            studentFinanceDTO.setIsPaid(true);
        }
        if(amount.compareTo(factorCost) < 0 ) {
            log.debug("Amount Equals : {} " , amount);
            studentFinanceDTO.setCost(factorCost.subtract(amount));
        }
        addStudentPayment(studentId,amount) ;
        StudentFinance studentFinance = studentFinanceService.updateFactor(studentFinanceDTO) ;
        return mapper.toDTO(studentFinance) ;
    }

    public StudentPaymentDTO addStudentPayment (Long studentId , BigDecimal amount) {
        log.debug("Request to add StudentPayment ,studentId : {} , amount : {}" , studentId,amount);
        StudentFactorResponse studentFinance = studentFinanceService.getFactorByStudentId(studentId) ;
        StudentPaymentDTO studentPaymentDTO = new StudentPaymentDTO() ;

        BigDecimal InitialCost = studentFinance.getCost() ;
        studentPaymentDTO.setInitialCost(InitialCost);
        studentPaymentDTO.setStudentFinanceId(studentFinance.getInvoiceCode());
        studentPaymentDTO.setAmount(amount);
        studentPaymentDTO.setNewCost(InitialCost.subtract(amount));

        return studentPaymentService.addPayment(studentPaymentDTO) ;
    }
}
