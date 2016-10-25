package com.sathish.messagebroker;

/**
 * Declare all the one to many exchange, queue, binding & routing key here
 * @author Sathish Thangathurai
 *
 */
public interface RabbitMQConstant {

	/** Queue Name */
	String TASK_QUEUE_NAME = "tasks.queue";
	
	/** Topic Exchange name */
	String TOPIC_EXCHANGE_NAME = "connected2fiber";
	
	/** Bind key to bind data from exchange to queue */
	String BIND_KEY = "connected2fiber.key";
	
	/** Routing key */
	String ROUTING_QUEUE_KEY = TASK_QUEUE_NAME;
}
