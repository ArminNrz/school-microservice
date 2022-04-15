package com.school.academic.dto.report;

import com.school.clients.finance.dto.StudentPaymentsReportResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportPaymentDTO extends ReportBaseDTO  {
    private List<StudentPaymentsReportResponse> paymentList ;
}
