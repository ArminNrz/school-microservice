package com.school.clients.finance;

import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "studentFinance",
        url = "${clients.finance.url}"
)
public interface StudentFinanceClient {

    @PostMapping("/api/finance/students")
    StudentFinanceRegisterResponse register(@RequestBody StudentFinanceRegisterRequest request);
}
