package com.school.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.student-notification}")
    private String studentNotificationQueue;

    @Value("${rabbitmq.routing-keys.internal-student-notification}")
    private String internalStudentNotificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue studentNotificationQueue() {
        return new Queue(this.studentNotificationQueue);
    }

    @Bean
    public Binding internalToStudentNotificationBinding() {
        return BindingBuilder
                .bind(studentNotificationQueue())
                .to(internalTopicExchange())
                .with(this.internalStudentNotificationRoutingKey);
    }
}
