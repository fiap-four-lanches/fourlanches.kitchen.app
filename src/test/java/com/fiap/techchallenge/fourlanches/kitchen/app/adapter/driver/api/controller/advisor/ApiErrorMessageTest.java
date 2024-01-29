package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.controller.advisor;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiErrorMessageTest {

    @Test
    void shouldStoreHttpStatusAndErrorMessageInObject() {
        // given
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
        String expectedMessage = "Bad request";

        // when
        ApiErrorMessage errorMessage = new ApiErrorMessage(expectedStatus, expectedMessage);

        // then
        assertEquals(expectedStatus, errorMessage.getStatus());
        assertEquals(List.of(expectedMessage), errorMessage.getErrors());
    }

    @Test
    void shouldInitializeErrorsWithSingleErrorMessage() {
        // given
        String expectedMessage = "Bad request";

        // when
        ApiErrorMessage errorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, expectedMessage);

        // then
        assertEquals(List.of(expectedMessage), errorMessage.getErrors());
    }
}