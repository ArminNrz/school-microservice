package com.school.amqp;

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

    @Value("${rabbitMQ.queues.student-notification-pre}")
    private String studentNotificationPreQueue;

    @Value("${rabbitmq.routing-keys.internal-student-notification}")
    private String internalStudentNotificationRoutingKey;

    @Value("${rabbitMQ.routing-keys.internal-student-notification-pre}")
    private String internalStudentNotificationPreRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue studentNotificationQueue() {
        return new Queue(this.studentNotificationQueue);
    }

    @Bean
    public Queue studentNotificationPreQueue() {
        return new Queue(this.studentNotificationPreQueue);
    }

    @Bean
    public Binding internalToStudentNotificationBinding() {
        return BindingBuilder
                .bind(studentNotificationQueue())
                .to(internalTopicExchange())
                .with(this.internalStudentNotificationRoutingKey);
    }

    @Bean
    public Binding internalToStudentNotificationPreBinding() {
        return BindingBuilder
                .bind(studentNotificationPreQueue())
                .to(internalTopicExchange())
                .with(this.internalStudentNotificationPreRoutingKey);
    }
}
