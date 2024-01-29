package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.api.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InternalServerErrorExceptionTest {

    @Test
    public void shouldCreateNewInternalServerErrorExceptionWithEmptyConstructor() {
        Exception exception = new InternalServerErrorException();
        assertNull(exception.getMessage());
    }

    @Test
    public void shouldCreateNewInternalServerErrorExceptionWithMessageInConstructor() {
        String message = "Test message";
        Exception exception = new InternalServerErrorException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldCreateNewInternalServerErrorExceptionWithCauseInConstructor() {
        Exception cause = new Exception("Cause");
        InternalServerErrorException exception = new InternalServerErrorException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldCreateNewInternalServerErrorExceptionWithMessageAndCauseInConstructor() {
        String message = "Test message";
        Exception cause = new Exception("Cause");
        InternalServerErrorException exception = new InternalServerErrorException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldCreateNewInternalServerErrorExceptionWithMessageCauseSuppressionWritableStackTraceInConstructor() {
        String message = "Test message";
        Exception cause = new Exception("Cause");
        InternalServerErrorException exception = new InternalServerErrorException(message, cause, true, true);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}