package com.processmind.common.exception;

import com.processmind.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle validation errors from @Valid
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.warn("Validation failed: {}", errors);

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        "Validation failed",
                        errors,
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle constraint violation exceptions
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {

        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        log.warn("Constraint violation: {}", errors);

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        "Validation failed",
                        errors,
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle missing request parameters
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Void>> handleMissingParams(
            MissingServletRequestParameterException ex, HttpServletRequest request) {

        log.warn("Missing parameter: {}", ex.getParameterName());

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        String.format("Missing required parameter: %s", ex.getParameterName()),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle method argument type mismatch
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        log.warn("Type mismatch: {}", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        String.format("Invalid parameter type: %s", ex.getName()),
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle unsupported HTTP methods
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {

        log.warn("Method not supported: {}", ex.getMethod());

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.error(
                        String.format("Method %s not supported", ex.getMethod()),
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle resource not found
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(
            NoHandlerFoundException ex, HttpServletRequest request) {

        log.warn("Resource not found: {}", ex.getRequestURL());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                        "Resource not found",
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle access denied (for authentication)
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiResponse<Void>> handleAccessDenied(
            AccessDeniedException ex, HttpServletRequest request) {

        log.warn("Access denied: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(
                        "Access denied",
                        HttpStatus.FORBIDDEN.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle malformed JSON
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Void>> handleMalformedJson(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        log.warn("Malformed JSON: {}", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(
                        "Invalid request body",
                        HttpStatus.BAD_REQUEST.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle custom application exceptions
     */
    @ExceptionHandler(ProcessMindException.class)
    public ResponseEntity<ApiResponse<Void>> handleProcessMindException(
            ProcessMindException ex, HttpServletRequest request) {

        log.error("Application exception: {}", ex.getMessage(), ex);

        HttpStatus status = ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(
                        ex.getMessage(),
                        status.value(),
                        request.getRequestURI()
                ));
    }

    /**
     * Handle all other exceptions
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<Void>> handleAllExceptions(
            Exception ex, HttpServletRequest request) {

        log.error("Unexpected error occurred", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        "An unexpected error occurred",
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        request.getRequestURI()
                ));
    }
}