package com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductionOrderStatus {
    RECEIVED("received"),
    QUEUED("queued"),
    IN_PREPARATION("in_preparation"),
    FINISHED("finished");

    private final String value;

    ProductionOrderStatus(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
