package com.project.capstone.util;

import java.time.LocalDateTime;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.common.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> ResponseEntity<Object> build(AppConstant.ResponseCode responseCode, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(responseCode, data), httpStatus);
    }

    private static <T> ApiResponse<T> build(AppConstant.ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .responseCode(responseCode.name())
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }
    
}
