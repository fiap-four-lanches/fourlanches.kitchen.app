package com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;

import java.util.List;

public interface ProductionRepository {

    ProductionOrder create(ProductionOrder productionOrder);

    ProductionOrder getProductionOrderByOrderId(Long orderId);

    List<ProductionOrder> getAllProductionOrdersNotFinished();
}
