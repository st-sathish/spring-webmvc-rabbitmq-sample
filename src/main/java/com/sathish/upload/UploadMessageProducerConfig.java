package com.sathish.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.sathish.messagebroker.producer.RabbitMQProducerConfig;

@Configuration
@Import(RabbitMQProducerConfig.class)
public class UploadMessageProducerConfig {

	/** The Logger */
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerConfig.class);
	
	private final RabbitMQProducerConfig producerConfig;
	
	public UploadMessageProducerConfig(RabbitMQProducerConfig producerConfig) {
		this.producerConfig = producerConfig;
	}
	
    @Bean
    public Queue importQueue() {
    	logger.info("Bean {} created ", Queue.class.getName());
        return new Queue(UploadConstant.IMPORT_QUEUE_NAME);
    }
	
	@Bean
	public Binding binding() {
		logger.info("Bean {} created ", Binding.class.getName());
		return BindingBuilder.bind(importQueue()).to(this.producerConfig.topicExchange()).with(UploadConstant.ROUTING_IMPORT_QUEUE_KEY);
	}
}
