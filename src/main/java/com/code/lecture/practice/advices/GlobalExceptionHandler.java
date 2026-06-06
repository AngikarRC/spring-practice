package com.code.lecture.practice.advices;

import com.code.lecture.practice.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException ex){
        ApiError apiError =
                        ApiError.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(ex.getMessage())
                                .build();
        return buildErrorResponseEntity(apiError);
    }

    //handle all RunTimeEcxeptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception ex){
        ApiError apiError =
                ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage())
                        .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * handle multiple exceptions e.g validation exceptions
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException ex){

        List<String> errors =
                ex.getBindingResult().getAllErrors()
                        .stream()
                        .map(objectError -> objectError.getDefaultMessage())
                        .toList();

        ApiError apiError =
                ApiError.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(errors.toString())
                        .subErrors(errors)
                        .build();

        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());

    }

}
