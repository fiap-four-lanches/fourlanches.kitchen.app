package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.expcetion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductionOrderNotFoundExceptionTest {
    @Test
    public void shouldCreateNewProductionOrderNotFoundExceptionWithEmptyConstructor() {
        Exception exception = new ProductionOrderNotFoundException();
        assertNull(exception.getMessage());
    }

    @Test
    public void shouldCreateNewProductionOrderNotFoundExceptionWithMessageInConstructor() {
        String message = "Test message";
        Exception exception = new ProductionOrderNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldCreateNewProductionOrderNotFoundExceptionWithCauseInConstructor() {
        Exception cause = new Exception("Cause");
        ProductionOrderNotFoundException exception = new ProductionOrderNotFoundException(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldCreateNewProductionOrderNotFoundExceptionWithMessageAndCauseInConstructor() {
        String message = "Test message";
        Exception cause = new Exception("Cause");
        ProductionOrderNotFoundException exception = new ProductionOrderNotFoundException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldCreateNewProductionOrderNotFoundExceptionWithMessageCauseSuppressionWritableStackTraceInConstructor() {
        String message = "Test message";
        Exception cause = new Exception("Cause");
        ProductionOrderNotFoundException exception = new ProductionOrderNotFoundException(message, cause, true, true);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}