package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    public final static String X_REQUEST_ID = "X-Request-Id";
    private final String orderStatusQueue;
    private final String inPreparationQueue;
    private final String finishedQueue;
    private final String orderCancelQueue;
    private final String exchangeChannel;

    public QueueConfiguration(@Value("${queue.order.status.name}") String orderStatusQueue,
                              @Value("${queue.in-preparation.name}") String inPreparationQueue,
                              @Value("${queue.finished.name}") String finishedQueue,
                              @Value("${queue.order.cancel.name}") String orderCancelQueue,
                              @Value("${queue.exchange.name}") String exchangeChannel) {
        this.orderStatusQueue = orderStatusQueue;
        this.inPreparationQueue = inPreparationQueue;
        this.finishedQueue = finishedQueue;
        this.orderCancelQueue = orderCancelQueue;
        this.exchangeChannel = exchangeChannel;
    }

    @Bean
    public Queue orderStatusQueue() {
        return QueueBuilder.durable(orderStatusQueue).build();
    }

    @Bean
    public Queue inPreparationQueue() {
        return QueueBuilder.durable(inPreparationQueue).build();
    }

    @Bean
    public Queue finishedQueue() {
        return QueueBuilder.durable(finishedQueue).build();
    }

    @Bean
    public Queue orderCancelQueue() {
        return QueueBuilder.durable(orderCancelQueue).build();
    }

    @Bean
    public Binding orderStatusBinding() {
        return BindingBuilder.bind(orderStatusQueue()).to(channelExchange()).with(orderStatusQueue);
    }

    @Bean
    public Binding inPreparationBinding() {
        return BindingBuilder.bind(inPreparationQueue()).to(channelExchange()).with(inPreparationQueue);
    }

    @Bean
    public Binding finishedBinding() {
        return BindingBuilder.bind(finishedQueue()).to(channelExchange()).with(finishedQueue);
    }

    @Bean
    public Binding orderCancelBinding() {
        return BindingBuilder.bind(orderCancelQueue()).to(channelExchange()).with(orderCancelQueue);
    }

    @Bean
    public DirectExchange channelExchange() {
        return new DirectExchange(exchangeChannel, true, false);
    }
}
