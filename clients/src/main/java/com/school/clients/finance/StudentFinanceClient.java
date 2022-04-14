package com.school.clients.finance;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "studentFinance",
        url = "${clients.finance.url}"
)
public interface StudentFinanceClient {

    @PostMapping("/api/finance/students")
    StudentFinanceRegisterResponse register(@RequestBody StudentFinanceRegisterRequest request);

    @GetMapping("/api/finance/students/{studentId}")
    StudentFactorResponse getFactor(@PathVariable("studentId") Long id);

    @GetMapping("/api/finance/students/getPaidFactor")
    List<StudentFinanceRegisterResponse> getPaidFactor() ;

}
