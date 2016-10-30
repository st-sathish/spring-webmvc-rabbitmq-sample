package com.sathish.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class UploadConsumerMessage implements MessageListener {

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(UploadConsumerMessage.class);

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		logger.info("onReceived message {}", new String(message.getBody()));
	}

}
