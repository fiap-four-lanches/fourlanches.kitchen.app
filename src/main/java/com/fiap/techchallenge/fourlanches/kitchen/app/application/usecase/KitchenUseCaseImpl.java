package com.fiap.techchallenge.fourlanches.kitchen.app.application.usecase;

import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrderStatus;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository.ProductionRepository;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.usecase.KitchenUseCase;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.valueobject.ProductionOrderIntent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KitchenUseCaseImpl implements KitchenUseCase {

    private final ProductionRepository productionRepository;

    @Override
    public ProductionOrder addOrderToProductionQueue(ProductionOrderIntent productionOrderIntent) {
        if (productionOrderIntent == null) {
            throw new IllegalArgumentException("production order intent is null");
        }
        return productionRepository.setOrCreate(productionOrderIntent.toProductionOrder());
    }

    @Override
    public ProductionOrder getProductionOrderByOrderId(Long orderId) {
        return productionRepository.getProductionOrderByOrderId(orderId);
    }

    @Override
    public List<ProductionOrder> getAllProductionOrdersNotFinished() {
        return productionRepository.getAllProductionOrdersNotFinished();
    }

    @Override
    public ProductionOrder updateProductionOrderStatusById(Long orderId, ProductionOrderStatus status) {
        var productionOrder = productionRepository.getProductionOrderByOrderId(orderId);
        productionOrder.setStatus(status);
        return productionRepository.setOrCreate(productionOrder);
    }
}
