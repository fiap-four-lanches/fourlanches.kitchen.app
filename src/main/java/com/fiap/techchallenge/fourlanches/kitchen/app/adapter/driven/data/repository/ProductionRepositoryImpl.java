package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.repository;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.ProductionOrderJpaRepository;
import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity.ProductionOrderJpaEntity;
import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.expcetion.ProductionOrderNotFound;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.entity.ProductionOrder;
import com.fiap.techchallenge.fourlanches.kitchen.app.domain.repository.ProductionRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductionRepositoryImpl implements ProductionRepository {

    private ProductionOrderJpaRepository jpaRepository;

    @Override
    public ProductionOrder setOrCreate(ProductionOrder productionOrder) {
        var newProductionOrder = jpaRepository.save(ProductionOrderJpaEntity.fromProductionOrder(productionOrder));
        return newProductionOrder.toProductionOrder();
    }

    @Override
    public ProductionOrder getProductionOrderByOrderId(Long orderId) {
        Optional<ProductionOrderJpaEntity> optionalProductionOrder = jpaRepository.getProductionOrderJpaEntityByOrderId(orderId);
        return optionalProductionOrder.map(ProductionOrderJpaEntity::toProductionOrder)
                .orElseThrow(ProductionOrderNotFound::new);
    }

    @Override
    public List<ProductionOrder> getAllProductionOrdersNotFinished() {
        return jpaRepository.getAllProductionOrderNotFinished()
                .stream()
                .map(ProductionOrderJpaEntity::toProductionOrder)
                .toList();
    }
}
