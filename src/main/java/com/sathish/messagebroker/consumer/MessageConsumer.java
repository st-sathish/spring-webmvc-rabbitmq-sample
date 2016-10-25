package com.sathish.messagebroker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible to receive message from the message broker
 * @author Sathish Thangathurai
 *
 */
public class MessageConsumer implements MessageConsumerListener {

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	
	@Override
	public void onReceived(String message) {
		// TODO Auto-generated method stub
		logger.info("onReceived message {}", message);
		System.out.println("Message Received: " + message);
	}

}
