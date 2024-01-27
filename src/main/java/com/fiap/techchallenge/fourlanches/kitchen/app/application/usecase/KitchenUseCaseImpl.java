package com.fiap.techchallenge.fourlanches.kitchen.app.application.usecase;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository.ProductionRepository;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KitchenUseCaseImpl implements KitchenUseCase {

    private final ProductionRepository productionRepository;


    @Override
    public ProductionOrder addOrderToProductionQueue(ProductionOrderIntent productionOrderIntent) {
        return productionRepository.create(productionOrderIntent.toProductionOrder());
    }

    @Override
    public ProductionOrder getProductionOrderById(Long id) {
        return null;
    }

    @Override
    public ProductionOrder getProductionOrderByOrderId(Long id) {
        return null;
    }

    @Override
    public ProductionOrder updateProductionOrderStatusById(Long id, ProductionOrderStatus status) {
        return null;
    }
}
