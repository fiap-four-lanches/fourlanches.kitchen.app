package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity.ProductionOrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductionOrderJpaRepository extends JpaRepository<ProductionOrderJpaEntity, Long> {

}
