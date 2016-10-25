package com.sathish.messagebroker.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sathish.messagebroker.config.RabbitMQConfiguration;
import com.sathish.messagebroker.RabbitMQConstant;

/**
 * Import RabbitMQConfiguration class to access core properties
 * @author Sathish Thangathurai
 *
 */
@Configuration
@Import(RabbitMQConfiguration.class)
public class RabbitMQProducerConfig implements RabbitMQConstant {

	private final RabbitMQConfiguration config;
	
	public RabbitMQProducerConfig(RabbitMQConfiguration config) {
		this.config = config;
	}
	
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(config.connectionFactory());
        template.setRoutingKey(ROUTING_QUEUE_KEY);
        template.setQueue(TASK_QUEUE_NAME);
        template.setMessageConverter(config.jsonMessageConverter());
        template.setExchange(TOPIC_EXCHANGE_NAME);
        return template;
    }

    @Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
	}
	
	@Bean
	public Binding binding(TopicExchange topicExchange, Queue queue) {
		return BindingBuilder.bind(queue).to(topicExchange).with(BIND_KEY);
	}
	
    @Bean
    public Queue tasksQueue() {
        return new Queue(TASK_QUEUE_NAME, false);
    }
}
