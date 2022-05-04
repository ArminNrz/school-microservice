package com.school.clients.exception;

import lombok.Data;

@Data
public class ExceptionMessage {
    private String title;
    private String status;
    private String detail;
}
