package com.school.clients.academic;

import com.school.clients.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "studentAcademic",
        url = "${clients.academic.url}",
        configuration = FeignConfiguration.class
)
public interface StudentAcademicClient {

    @GetMapping("/api/academic/students")
    List<StudentDTO> getAll(
            @RequestParam("accessUnitRegistration") Boolean accessRegister,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );
}
