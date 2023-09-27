package com.bakery.backend.infrastructure.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bakery.backend.domain.exceptions.NotFoundException;
import com.bakery.backend.infrastructure.models.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(NotFoundException ex) {
        // Crie uma resposta HTTP com o código de erro e a mensagem desejados
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String errorMessage = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), errorMessage);

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    // Adicione outros manipuladores de exceções, se necessário
}

