package com.school.finance.service.thirdparty;

import com.school.clients.academic.StudentAcademicClient;
import com.school.clients.academic.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcademicClientService {

    private final StudentAcademicClient studentAcademicClient;

    public List<StudentDTO> getNotAccessRegisterStudents(int page, int size) {
        log.debug("Try to send request to academic-service to get student with no-access to register units, pageSize: {}", size);
        List<StudentDTO> result = studentAcademicClient.getAll(false, page, size);
        log.info("Get list of no-access register students: {}", result);
        return result;
    }
}
