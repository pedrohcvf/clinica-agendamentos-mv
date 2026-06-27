package com.pedro.clinica.exception;

import com.pedro.clinica.exception.custom.ConflictException;
import com.pedro.clinica.exception.custom.ResourceNotFoundException;
import com.pedro.clinica.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {

    // VALIDAÇÃO DE DADOS
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerResourceNotFoundException(ResourceNotFoundException exception){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(404, exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    // VALIDAÇÃO DE CONFLITO
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> handlerConflictException(ConflictException exception){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(409, exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }



}
