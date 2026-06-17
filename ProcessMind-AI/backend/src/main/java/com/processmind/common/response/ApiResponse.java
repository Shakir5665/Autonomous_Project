package com.processmind.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String timestamp;
    private Integer statusCode;
    private String path;

    // Success response with data
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    // Success response with data only
    public static <T> ApiResponse<T> success(T data) {
        return success(data, "Operation successful");
    }

    // Success response without data
    public static <T> ApiResponse<T> success() {
        return success(null, "Operation successful");
    }

    // Error response
    public static <T> ApiResponse<T> error(String message, Integer statusCode, String path) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now().toString())
                .statusCode(statusCode)
                .path(path)
                .build();
    }

    // Error response with data
    public static <T> ApiResponse<T> error(String message, T data, Integer statusCode, String path) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now().toString())
                .statusCode(statusCode)
                .path(path)
                .build();
    }
}