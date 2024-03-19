package com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductionStatusNotifier {
    void notifyOrderInPreparation(Long orderId) throws JsonProcessingException;
    void notifyOrderFinished(Long orderId) throws JsonProcessingException;
}
