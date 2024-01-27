package com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;

public interface KitchenUseCase {

    ProductionOrder addOrderToProductionQueue(ProductionOrderIntent productionOrderIntent);

    ProductionOrder getProductionOrderById(Long id);

    ProductionOrder getProductionOrderByOrderId(Long id);

    ProductionOrder updateProductionOrderStatusById(Long id, ProductionOrderStatus status);
}
