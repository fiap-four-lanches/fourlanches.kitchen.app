package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QueueConfiguration {

    public final static String X_REQUEST_ID = "X-Request-Id";
    @Value("${queue.order.status.name}")
    private final String orderStatusQueue;
    @Value("${queue.in-preparation.name}")
    private final String inPreparationQueue;
    @Value("${queue.finished.name}")
    private final String finishedQueue;
    @Value("${queue.order.cancel.name}")
    private final String orderCancelQueue;
    @Value("${queue.exchange.name}")
    private final String exchangeChannel;

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
