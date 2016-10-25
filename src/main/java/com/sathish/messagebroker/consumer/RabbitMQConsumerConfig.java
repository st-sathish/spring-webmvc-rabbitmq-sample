package com.sathish.messagebroker.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sathish.messagebroker.RabbitMQConstant;
import com.sathish.messagebroker.config.RabbitMQConfiguration;

/**
 * 
 * @author Sathish Thangathurai
 *
 */
//@Configuration
//@Import(RabbitMQConfiguration.class)
public class RabbitMQConsumerConfig {

	private final RabbitMQConfiguration config;

	/** receiver callback method */
	private static final String RECEVIER_CALLBACK_METHOD_NAME = "onReceived";
	
	public RabbitMQConsumerConfig(RabbitMQConfiguration config) {
		this.config = config;
	}
	
	//@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(RabbitMQConstant.TASK_QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		container.setMessageConverter(this.config.jsonMessageConverter());
		return container;
	}

    //@Bean
    public MessageConsumerListener consumer() {
        return new MessageConsumer();
    }

    //@Bean
	public MessageListenerAdapter listenerAdapter(MessageConsumerListener consumer) {
		return new MessageListenerAdapter(consumer, RECEVIER_CALLBACK_METHOD_NAME);
	}
}
