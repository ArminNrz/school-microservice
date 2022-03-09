package com.school.baseLayer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice implements ProblemHandling {}
