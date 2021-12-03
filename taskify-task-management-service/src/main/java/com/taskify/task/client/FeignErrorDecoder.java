package com.taskify.task.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskify.task.exception.FeignClientException;
import com.taskify.task.exception.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public Exception decode(String resource, Response response) {

        ErrorResponse errorResponse = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);

        switch (response.status()) {
            case 400: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.BAD_REQUEST);
            }
            case 401: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.UNAUTHORIZED);
            }
            case 403: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.FORBIDDEN);
            }
            case 404: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.NOT_FOUND);
            }
            case 409: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.CONFLICT);
            }
            case 500: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            default: {
                return new FeignClientException(errorResponse.getMessage(), errorResponse.getTitle(), resource, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
