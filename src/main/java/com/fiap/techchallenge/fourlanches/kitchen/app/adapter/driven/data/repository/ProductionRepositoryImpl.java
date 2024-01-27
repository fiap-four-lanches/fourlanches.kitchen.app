package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.repository;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository.ProductionRepository;

import java.util.List;

public class ProductionRepositoryImpl implements ProductionRepository {
    @Override
    public ProductionOrder create(ProductionOrder productionOrder) {
        return null;
    }

    @Override
    public ProductionOrder getProductionOrderByOrderId(Long orderId) {
        return null;
    }

    @Override
    public List<ProductionOrder> getAllProductionOrdersNotFinished() {
        return null;
    }
}
