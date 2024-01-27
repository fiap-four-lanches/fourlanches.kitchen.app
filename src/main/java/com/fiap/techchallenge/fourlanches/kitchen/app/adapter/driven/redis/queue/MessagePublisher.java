package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driven.redis.queue;

public interface MessagePublisher {
    void publish(final String message);
}
