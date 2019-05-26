package com.mykafka.projects.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mykafka.projects.producer.KafkaSender;
import com.mykafka.projects.util.KafkaConstants;

import lombok.extern.slf4j.Slf4j;
  
@RestController
@Slf4j
public class KafkaWebController implements KafkaConstants{
	public static final String DEMO_TOPIC = "demo-topic";

	final static Logger log = LoggerFactory.getLogger(KafkaWebController.class);

	@Autowired
	private KafkaSender kafkaSender;
	
	/**
	 *  Kafka message sender
	 * @param message
	 * @return
	 */
	@GetMapping(SEND_MESSAGE)
	public String sendMessage(@PathVariable("message") String message) {
		log.debug("input message: "+message);
		
		kafkaSender.send(DEMO_TOPIC, message);
		return MESSAGE_SENT;
	}
}
