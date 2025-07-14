package ru.app.rentservice.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new RuntimeException("Bad request");
            case 404 -> new RuntimeException("Not found");
            case 500 -> new RuntimeException("Internal server error");
            default -> new RuntimeException(response.reason());
        };
    }
}
