package com.sathish.messagebroker.producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sathish.messagebroker.config.RabbitMQConfiguration;

/**
 * Import RabbitMQConfiguration class to access core properties
 * @author Sathish Thangathurai
 *
 */
@Configuration
@Import(RabbitMQConfiguration.class)
public class RabbitMQProducerConfig {

	/** The Logger */
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerConfig.class);
	
	private final RabbitMQConfiguration config;
	
	public RabbitMQProducerConfig(RabbitMQConfiguration config) {
		this.config = config;
	}
    
    @Bean
	public AmqpAdmin admin() {
    	logger.info("Bean {} created ", RabbitAdmin.class.getName());
		AmqpAdmin admin = new RabbitAdmin(this.config.connectionFactory());
		return admin;
	}
    
    @Bean
	public TopicExchange topicExchange() {
    	logger.info("Bean {} created ", TopicExchange.class.getName());
    	TopicExchange topicExchange = new TopicExchange(RabbitMQProducerConstant.TOPIC_EXCHANGE_NAME);
		return topicExchange;
	}
	
	@Bean
    public RabbitTemplate template() {
		logger.info("Bean {} created ", RabbitTemplate.class.getName());
    	RabbitTemplate template = new RabbitTemplate(this.config.connectionFactory());
    	template.setExchange(RabbitMQProducerConstant.TOPIC_EXCHANGE_NAME);
        template.setMessageConverter(config.jsonMessageConverter());
        return template;
    }
}
