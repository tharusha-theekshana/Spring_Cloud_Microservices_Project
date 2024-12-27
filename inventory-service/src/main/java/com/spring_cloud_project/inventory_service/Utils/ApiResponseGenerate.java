package com.spring_cloud_project.inventory_service.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseGenerate {

    public ApiResponseGenerate() {
    }

    public static <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(T data, String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(status.value(), message, data);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> createSuccessResponseForList(T data, String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(status.value(), message, data);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> createErrorResponse(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(status.value(), message, null);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> createErrorResponseForList(T data,String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(status.value(), message, data);
        return new ResponseEntity<>(response, status);
    }
}
