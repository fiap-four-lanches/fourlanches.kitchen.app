package com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository;

import com.fasterxml.jackson.core.JacksonException;

public interface ProductionStatusNotifier {
    void notifyOrderInPreparation(Long orderId);
    void notifyOrderFinished(Long orderId) ;
}
