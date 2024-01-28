package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.expcetion;

public class ProductionOrderNotFound extends RuntimeException{
    public ProductionOrderNotFound() {
    }

    public ProductionOrderNotFound(String message) {
        super(message);
    }

    public ProductionOrderNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductionOrderNotFound(Throwable cause) {
        super(cause);
    }

    public ProductionOrderNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
