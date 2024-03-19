package com.fiap.techchallenge.fourlanches.kitchen.app.adapter.driver.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class QueueConfigurationTest {

    private final static String ORDER_STATUS_QUEUE = "order_status_queue";
    private final static String IN_PREPARATION_QUEUE = "in_preparation_queue";
    private final static String FINISHED_QUEUE = "finished_queue";
    private final static String ORDER_CANCEL_QUEUE = "order_cancel_queue";
    private final static String QUEUE_EXCHANGE = "queue_exchange";
    private QueueConfiguration queueConfiguration;
    private DirectExchange exchange;

    @BeforeEach
    public void setUp() {
        this.queueConfiguration = new QueueConfiguration(ORDER_STATUS_QUEUE,
                IN_PREPARATION_QUEUE,
                FINISHED_QUEUE,
                ORDER_CANCEL_QUEUE,
                QUEUE_EXCHANGE);
        exchange = this.queueConfiguration.channelExchange();
    }

    @Test
    public void shouldReturnCorrectNameForOrderStatusQueue() {
        // Act
        Queue queue = queueConfiguration.orderStatusQueue();

        // Assert
        assertThat(queue.getName()).isEqualTo(ORDER_STATUS_QUEUE);
    }

    @Test
    public void shouldReturnCorrectNameForInPreparationQueue() {
        // Act
        Queue queue = queueConfiguration.inPreparationQueue();

        // Assert
        assertThat(queue.getName()).isEqualTo(IN_PREPARATION_QUEUE);
    }

    @Test
    public void shouldReturnCorrectNameForFinishedQueue() {
        // Act
        Queue queue = queueConfiguration.finishedQueue();

        // Assert
        assertThat(queue.getName()).isEqualTo(FINISHED_QUEUE);
    }

    @Test
    public void shouldReturnCorrectNameForOrderCancelQueue() {
        // Act
        Queue queue = queueConfiguration.orderCancelQueue();

        // Assert
        assertThat(queue.getName()).isEqualTo(ORDER_CANCEL_QUEUE);
    }

    @Test
    public void shouldReturnCorrectBindingForOrderStatusQueue() {
        // Act
        Binding binding = queueConfiguration.orderStatusBinding();

        // Assert
        assertThat(binding.getExchange()).isEqualTo(exchange.getName());
        assertThat(binding.getDestination()).isEqualTo(ORDER_STATUS_QUEUE);
    }

    @Test
    public void shouldReturnCorrectBindingForInPreparationQueue() {
        // Act
        Binding binding = queueConfiguration.inPreparationBinding();

        // Assert
        assertThat(binding.getExchange()).isEqualTo(exchange.getName());
        assertThat(binding.getDestination()).isEqualTo(IN_PREPARATION_QUEUE);
    }

    @Test
    public void shouldReturnCorrectBindingForFinishedQueue() {
        // Act
        Binding binding = queueConfiguration.finishedBinding();

        // Assert
        assertThat(binding.getExchange()).isEqualTo(exchange.getName());
        assertThat(binding.getDestination()).isEqualTo(FINISHED_QUEUE);
    }

    @Test
    public void shouldReturnCorrectBindingForOrderCancelQueue() {
        // Act
        Binding binding = queueConfiguration.orderCancelBinding();

        // Assert
        assertThat(binding.getExchange()).isEqualTo(exchange.getName());
        assertThat(binding.getDestination()).isEqualTo(ORDER_CANCEL_QUEUE);
    }

}