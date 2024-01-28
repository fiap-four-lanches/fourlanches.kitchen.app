package com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;

import java.util.List;

public interface KitchenUseCase {

    ProductionOrder addOrderToProductionQueue(ProductionOrderIntent productionOrderIntent);

    ProductionOrder getProductionOrderByOrderId(Long orderId);

    List<ProductionOrder> getAllProductionOrdersNotFinished();

    ProductionOrder updateProductionOrderStatusById(Long orderId, ProductionOrderStatus status);
}
