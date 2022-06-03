package com.school.finance.service.highLevel;

import com.school.clients.finance.dto.ChargeWalletRequest;
import com.school.clients.finance.dto.ChargeWalletResponse;
import com.school.finance.domain.StudentFinance;
import com.school.finance.domain.StudentPayment;
import com.school.finance.domain.StudentWallet;
import com.school.finance.domain.WalletLog;
import com.school.finance.dto.student.StudentFinanceDTO;
import com.school.finance.dto.student.StudentFinancePaymentDTO;
import com.school.finance.mapper.StudentFinanceMapper;
import com.school.finance.service.entity.StudentFinanceService;
import com.school.finance.service.entity.StudentPaymentService;
import com.school.finance.service.entity.StudentWalletService;
import com.school.finance.service.entity.WalletLogService;
import com.school.finance.service.produce.StudentFinanceProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceManager {

    private final StudentFinanceService studentFinanceService;
    private final StudentPaymentService studentPaymentService;
    private final StudentFinanceMapper studentFinanceMapper;
    private final StudentFinanceProducer studentFinanceProducer;
    private final StudentWalletService studentWalletService ;
    private final WalletLogService walletLogService ;

    @Transactional
    public StudentFinanceDTO payment(StudentFinancePaymentDTO paymentDTO) {
        log.debug("Request to pay Factor for studentId {}, and amount : {} " , paymentDTO.getStudentId() , paymentDTO.getAmount());

        BigDecimal amount = paymentDTO.getAmount();

        Optional<StudentFinance> studentFinanceOptional = studentFinanceService.getByStudentId(paymentDTO.getStudentId());
        if(studentFinanceOptional.isEmpty()) {
            log.error("Can not found studentFinance for this student");
            throw Problem.valueOf(Status.NOT_FOUND , "This Student has not any factor") ;
        }
        StudentFinance studentFinance = studentFinanceOptional.get();

        payFinance(amount, studentFinance);
        return studentFinanceMapper.toDTO(studentFinance);
    }

    private void payFinance(BigDecimal amount, StudentFinance studentFinance) {

        StudentFinance oldStudentFinance = studentFinance.clone();

        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            log.debug("Amount Equals Zero ... ");
            throw Problem.valueOf(Status.BAD_REQUEST , "The amount equals Zero !") ;
        }

        BigDecimal factorCost = studentFinance.getCost() ;
        if (amount.compareTo(factorCost) >= 0 ) {
            studentFinance.setCost(BigDecimal.ZERO);
            studentFinance.setIsPaid(true);
        }

        if(amount.compareTo(factorCost) < 0 ) {
            log.debug("Amount Equals : {} " , amount);
            studentFinance.setCost(factorCost.subtract(amount));
        }

        StudentPayment studentPayment = studentPaymentService.addPayment(studentFinance, oldStudentFinance, amount);
        studentFinanceService.update(studentFinance, studentPayment);

        log.debug("StudentFinance: {}, is paid with amount: {}", studentFinance.getId(), amount);

        if(studentFinance.getIsPaid())
            studentFinanceProducer.produceToStudentNotificationPreQueue(studentFinance);
    }

    @Transactional
    public ChargeWalletResponse chargeWallet(ChargeWalletRequest request) {
        log.debug("Request to charge the wallet : {}" , request);
        Optional<StudentWallet> wallet = studentWalletService.getByStudentId(request.getStudentId()) ;
        if(wallet.isEmpty())
            throw Problem.valueOf(Status.NOT_FOUND , "The student hasn't any wallet !") ;

        String walletId = wallet.get().getId() ;
        BigDecimal amount = request.getAmount() ;
        StudentWallet updatedWallet = studentWalletService.charge(walletId , amount) ;
        WalletLog walletLog = walletLogService.logTransaction(walletId , amount) ;

        ChargeWalletResponse response = new ChargeWalletResponse() ;
        response.setWalletId(walletId);
        response.setStudentId(request.getStudentId());
        response.setTransactionId(walletLog.getId());
        response.setCurrentBalance(updatedWallet.getBalance());
        return response ;
    }

}
