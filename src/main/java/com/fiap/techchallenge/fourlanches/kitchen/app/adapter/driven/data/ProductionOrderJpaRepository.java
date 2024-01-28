package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity.ProductionOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductionOrderJpaRepository extends JpaRepository<ProductionOrderJpaEntity, Long> {

    @Query("SELECT o FROM ProductionOrderJpaEntity o WHERE o.orderId = :orderId")
    Optional<ProductionOrderJpaEntity> getProductionOrderJpaEntityByOrderId(Long orderId);

    @Query("SELECT o FROM ProductionOrderJpaEntity o \n" +
            "WHERE o.status <> 'finished' \n" +
            "ORDER BY case \n" +
            "WHEN o.status = 'in_preparation' THEN 1\n" +
            "WHEN o.status = 'queued' THEN 2 END, o.createdAt ASC ")
    List<ProductionOrderJpaEntity> getAllProductionOrderNotFinished();
}
