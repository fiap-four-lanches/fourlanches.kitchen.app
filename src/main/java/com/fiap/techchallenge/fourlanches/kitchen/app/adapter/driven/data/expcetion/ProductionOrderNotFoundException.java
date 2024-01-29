package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.expcetion;

public class ProductionOrderNotFoundException extends RuntimeException{
    public ProductionOrderNotFoundException() {
    }

    public ProductionOrderNotFoundException(String message) {
        super(message);
    }

    public ProductionOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductionOrderNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProductionOrderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
