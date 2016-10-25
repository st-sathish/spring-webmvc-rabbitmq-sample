package com.sathish.common.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sathish.messagebroker.RabbitMQConstant;
import com.sathish.pojo.Response;

@Controller
public class IndexController {

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/** Rabbitmq Template */
	@Autowired private volatile RabbitTemplate rabbitTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView publish() throws IOException, TimeoutException {
		Response response = new Response();
		response.setMessage("Hello World");
		rabbitTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE_NAME, RabbitMQConstant.ROUTING_QUEUE_KEY, response);
		logger.info("Message {} has been published", response.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Message has been successfully published");
		mv.setViewName("index");
		return mv;
	}
}
