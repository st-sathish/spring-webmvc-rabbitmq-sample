package com.sathish.common.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sathish.messagebroker.producer.RabbitMQProducerConstant;
import com.sathish.pojo.Response;
import com.sathish.upload.UploadConstant;

@Controller
public class IndexController {

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/** Rabbitmq Template */
	@Autowired private RabbitTemplate template;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView publish() throws IOException, TimeoutException {
		Response response = new Response();
		response.setMessage("Hello World");
		response.setCode(HttpStatus.OK.value());
		response.setError("");
		template.convertAndSend(UploadConstant.ROUTING_IMPORT_QUEUE_KEY, response);
		
		logger.info("Message {} has been published", response.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Message has been successfully published");
		mv.setViewName("index");
		return mv;
	}
}
