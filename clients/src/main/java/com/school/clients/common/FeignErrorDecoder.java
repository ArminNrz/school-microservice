package com.school.clients.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.clients.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.io.IOException;
import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {

    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        ExceptionMessage message;

        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 400:
                return Problem.valueOf(Status.BAD_REQUEST, message.getDetail() != null ? message.getDetail() : "Bad Request");
            case 404:
                return Problem.valueOf(Status.NOT_FOUND, message.getDetail() != null ? message.getDetail() : "Not found");
            default:
                return errorDecoder.decode(methodKey, response);
        }

    }
}
