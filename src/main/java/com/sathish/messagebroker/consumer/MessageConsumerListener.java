package com.sathish.messagebroker.consumer;

/**
 * This will listen message broker to receive the messages
 * @author Sathish Thangathurai
 *
 */
public interface MessageConsumerListener {

	/**
	 * Receive the message in the form of string
	 * @param message
	 */
	public void onReceived(String message);
}
