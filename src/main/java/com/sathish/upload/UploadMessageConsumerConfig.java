package com.sathish.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sathish.messagebroker.config.RabbitMQConfiguration;

@Configuration
@Import(RabbitMQConfiguration.class)
public class UploadMessageConsumerConfig {

	/** The Logger */
	private static final Logger logger = LoggerFactory.getLogger(UploadMessageConsumerConfig.class);
	
	/** RabbitMQ Configuration */
	private final RabbitMQConfiguration config;
	
	public UploadMessageConsumerConfig(RabbitMQConfiguration config) {
		this.config = config;
	}
	
	@Bean
	public SimpleMessageListenerContainer container() {
		logger.info("Bean {} created ", SimpleMessageListenerContainer.class.getName());
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(this.config.connectionFactory());
		container.setQueueNames(UploadConstant.IMPORT_QUEUE_NAME);
		container.setMessageListener(this.consumer());
		container.setMessageConverter(this.config.jsonMessageConverter());
		return container;
	}

    @Bean
    public UploadConsumerMessage consumer() {
    	logger.info("Bean {} created ", UploadConsumerMessage.class.getName());
        return new UploadConsumerMessage();
    }
}
