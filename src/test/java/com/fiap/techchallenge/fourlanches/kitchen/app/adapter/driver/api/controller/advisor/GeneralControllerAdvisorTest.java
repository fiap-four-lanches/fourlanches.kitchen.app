package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.controller.advisor;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GeneralControllerAdvisorTest {

    @InjectMocks
    private GeneralControllerAdvisor advisor;

    @Mock
    private WebRequest webRequest;

    @Test
    void shouldHandleInternalServerErrorException() {
        var expectedError = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "an error happened");
        var expectedResponse = new ResponseEntity<>(expectedError, HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<ApiErrorMessage> actualResponse = advisor.handleInternalServerErrorException(webRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldHandleProductionOrderNotFoundException() {
        var ex = new EntityNotFoundException();
        var expectedError = new ApiErrorMessage(HttpStatus.NOT_FOUND, "production order not found");
        var expectedResponse = new ResponseEntity<>(expectedError, HttpStatus.NOT_FOUND);

        ResponseEntity<ApiErrorMessage> actualResponse = advisor.handleProductionOrderNotFoundException(ex, webRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldHandleEntityNotFoundException() {
        var ex = new EntityNotFoundException();
        var expectedError = new ApiErrorMessage(HttpStatus.NOT_FOUND, "entity not found");
        var expectedResponse = new ResponseEntity<>(expectedError, HttpStatus.NOT_FOUND);

        ResponseEntity<ApiErrorMessage> actualResponse = advisor.handleEntityNotFoundException(ex, webRequest);

        assertEquals(expectedResponse, actualResponse);
    }
}