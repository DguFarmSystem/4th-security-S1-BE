package com.farmsystem.sprout.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException e) {
        log.error("API 예외 발생: {}", e.getMessage(), e);
        
        Map<String, Object> response = new HashMap<>();
        response.put("error", "API 호출 실패");
        response.put("message", e.getMessage());
        response.put("statusCode", e.getStatusCode());
        
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }
    
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Map<String, Object>> handleWebClientException(WebClientResponseException e) {
        log.error("WebClient 예외 발생: {} - {}", e.getStatusCode(), e.getMessage(), e);
        
        Map<String, Object> response = new HashMap<>();
        response.put("error", "외부 API 호출 실패");
        response.put("message", e.getMessage());
        response.put("statusCode", e.getStatusCode().value());
        
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        log.error("일반 예외 발생: {}", e.getMessage(), e);
        
        Map<String, Object> response = new HashMap<>();
        response.put("error", "서버 내부 오류");
        response.put("message", "요청을 처리하는 중 오류가 발생했습니다.");
        response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
} 