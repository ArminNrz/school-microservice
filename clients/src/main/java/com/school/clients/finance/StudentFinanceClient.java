package com.school.clients.finance;

import com.school.clients.config.FeignConfiguration;
import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "studentFinance",
        url = "${clients.finance.url}",
        configuration = FeignConfiguration.class
)
public interface StudentFinanceClient {

    @PostMapping("/api/finance/students")
    StudentFinanceRegisterResponse register(@RequestBody StudentFinanceRegisterRequest request);

    @GetMapping("/api/finance/students/{studentId}")
    StudentFactorResponse getFactor(@PathVariable("studentId") Long id);

}
