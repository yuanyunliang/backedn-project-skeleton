package com.orange.eduback.common;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EduBaseException.class)
    public ResponseEntity<Object> handleGlobalException(EduBaseException e) {
        logger.error("GlobalException业务异常:{}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        logger.error("Exception系统异常:{}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    static class ErrorResponse {
        private int code;
        private String message;

        public ErrorResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

    }
}
