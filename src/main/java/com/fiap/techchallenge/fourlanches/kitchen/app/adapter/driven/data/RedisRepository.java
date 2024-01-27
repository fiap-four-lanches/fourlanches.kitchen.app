package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.data.entity.ProductionOrderQueuedRedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<ProductionOrderQueuedRedisEntity, String> {
}
