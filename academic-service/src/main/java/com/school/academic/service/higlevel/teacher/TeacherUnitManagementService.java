package com.school.academic.service.higlevel.teacher;

import com.school.academic.dto.teacher.TeacherDetailsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherUnitManagementService {
    private final TeacherUnitDetailsHandler detailsHandler;
    public TeacherDetailsDTO getDetailsByNationalCode(Long nationalCode) {
        return detailsHandler.getDetailsByNationalCode(nationalCode);
    }
}
