package com.sathish.messagebroker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:messagebroker.properties", ignoreResourceNotFound = true)
public class RabbitMQConfiguration {

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfiguration.class.getName());
	
	@Value("${com.sathish.rabbitmq.host:localhost}")
	private String HOST_NAME;

	@Value("${com.sathish.rabbitmq.port:5672}")
	private int PORT_NUMBER;
	
	@Value("${com.sathish.rabbitmq.username:guest}")
	private String USER_NAME;

	@Value("${com.sathish.rabbitmq.password:guest}")
	private String PASSWORD;
	
	@Bean
	public ConnectionFactory connectionFactory() {
		logger.info("Bean {} created ", ConnectionFactory.class.getName());
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(HOST_NAME);
		connectionFactory.setPort(PORT_NUMBER);
		connectionFactory.setUsername(USER_NAME);
		connectionFactory.setPassword(PASSWORD);
		return connectionFactory;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		logger.info("Bean {} created ", MessageConverter.class.getName());
		final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
	    //converter.setClassMapper(classMapper());
	    return converter;
	}
	
	@Bean
    public DefaultClassMapper classMapper() {
		logger.info("Bean {} created ", DefaultClassMapper.class.getName());
        DefaultClassMapper typeMapper = new DefaultClassMapper();
        return typeMapper;
    }
}
