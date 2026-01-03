package com.tbd.user_service.exception.handler;

import com.tbd.common.dto.ErrorResponse;
import com.tbd.user_service.exception.MaxAddressLimitExceedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice(basePackages = "com.tbd.user_service")
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserGlobalExceptionHandler {

    @ExceptionHandler(MaxAddressLimitExceedException.class)
    public ResponseEntity<ErrorResponse> handleMaxAddressLimitExceedException(MaxAddressLimitExceedException ex) {
        return getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(PathElementException.class)
    public ResponseEntity<ErrorResponse> handlePathElementException(PathElementException ex) {
        return getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .message(ex.getMessage())
//                .statusCode(500)
//                .timestamp(Instant.now())
//                .build();
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private ResponseEntity<ErrorResponse> getErrorResponse(String error, Integer statusCode) {

        ErrorResponse errorResponse = new ErrorResponse(
                error,
                statusCode,
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
    }

}
