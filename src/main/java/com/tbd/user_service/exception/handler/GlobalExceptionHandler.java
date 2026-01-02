package com.tbd.user_service.exception.handler;

import com.tbd.user_service.dto.ErrorResponse;
import com.tbd.user_service.exception.ResourceNotFoundInDbException;
import com.tbd.user_service.exception.UserSubNotFoundInHeaderException;
import com.tbd.user_service.util.Translator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.StringJoiner;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final Translator translator;

    @ExceptionHandler(ResourceNotFoundInDbException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundInDbException(ResourceNotFoundInDbException ex) {
        return getErrorResponse(ex.getMessage(), HttpStatus.NO_CONTENT.value());
    }

    @ExceptionHandler(UserSubNotFoundInHeaderException.class)
    public ResponseEntity<ErrorResponse> handleUserSubNotFoundInHeaderException(UserSubNotFoundInHeaderException ex) {
        return getErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        StringJoiner errors = new StringJoiner(", ");

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(translator.translate(errorMessage));
        });

        ErrorResponse errorResponse = new ErrorResponse(
                errors.toString(),
                null,
                HttpStatus.BAD_REQUEST.value(), Instant.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .statusCode(500)
                .errorCode("-1")
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(String errorCode, Integer statusCode) {

        ErrorResponse errorResponse = new ErrorResponse(
                translator.translate(errorCode),
                errorCode,
                statusCode,
                Instant.now()
        );
        log.error("{}: {}", errorResponse.getErrorCode(), errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
    }

}
