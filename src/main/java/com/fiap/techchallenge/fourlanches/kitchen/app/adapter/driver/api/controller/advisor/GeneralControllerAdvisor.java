package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.controller.advisor;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.expcetion.ProductionOrderNotFoundException;
import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.exception.InternalServerErrorException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GeneralControllerAdvisor {
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiErrorMessage> handleInternalServerErrorException(WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "an error happened");

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductionOrderNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleProductionOrderNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, "production order not found");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, "entity not found");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
