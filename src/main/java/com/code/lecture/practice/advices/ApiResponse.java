package com.code.lecture.practice.advices;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    @Pattern(regexp = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private T data;

    private ApiError apiError;

    public ApiResponse(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this.apiError = apiError;
    }
}
