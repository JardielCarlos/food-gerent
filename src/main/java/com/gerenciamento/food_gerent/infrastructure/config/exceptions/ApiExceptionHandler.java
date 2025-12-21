package com.gerenciamento.food_gerent.infrastructure.config.exceptions;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.gerenciamento.food_gerent.utils.exceptions.ExceptionUtil;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {
    // Exception Genéricas 
    @ExceptionHandler({
        MethodArgumentTypeMismatchException.class,
        MissingServletRequestParameterException.class,
        DataIntegrityViolationException.class,
        MethodArgumentNotValidException.class,
        ConversionFailedException.class,
    })
    public ResponseEntity<ProblemDetails> handleException(Exception ex, HttpServletRequest request) {
        ProblemDetails problemDetails = ExceptionUtil.getProblemDetails(request, ex);
        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    // Exception Específica
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        ProblemDetails problemDetails = new ProblemDetails(
            "Entidade não encontrada",
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return new ResponseEntity<>(problemDetails, HttpStatus.NOT_FOUND);
    }
}