package com.mykafka.projects.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import com.mykafka.projects.util.KafkaConstants;

public class KafkaReceiver implements KafkaConstants{

	@KafkaListener(topics = CUSTOM_TOPIC_1, groupId = "group_id")
	public void receive(String message) {
//		log.debug("payload consumed : "+message);
		System.out.print("recieved"+message);
	}
}
