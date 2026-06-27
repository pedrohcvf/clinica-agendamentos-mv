package com.pedro.clinica.exception;

import com.pedro.clinica.exception.custom.ConflictException;
import com.pedro.clinica.exception.custom.ResourceNotFoundException;
import com.pedro.clinica.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    // RECURSO NÃO ENCONTRADO
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handlerValidationException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorResponseDto error = new ErrorResponseDto(400, message, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
