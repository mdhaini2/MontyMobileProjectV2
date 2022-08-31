package com.projectV1.uniProject.Configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationRabbitMq {

    @Value("${grade.queue.name}")
    private String queueName;

    @Value("${grade.exchange.name}")
    private String exchange;

    @Value("${grade.routing.key}")
    private String routingKey;

    @Bean("grade-queue")
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean("grade-exchange")
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean("grade-queue-binding")
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();

    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }


}