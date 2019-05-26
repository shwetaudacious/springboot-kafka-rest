package com.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReceiveController {

	private CountDownLatch latch = new CountDownLatch(1);
	private Logger log = LoggerFactory.getLogger(ReceiveController.class);
	
	public CountDownLatch getCountDownLatch() {
		return latch;
	}

	@KafkaListener(topics = "demo-topic")
	public void getMessage(String message) {
		log.debug("recieved message: " + message);
	}
}
