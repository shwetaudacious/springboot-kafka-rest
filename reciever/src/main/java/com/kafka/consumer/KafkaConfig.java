package com.kafka.consumer;


import java.util.HashMap;
import java.util.Map;
 
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
 
@Configuration
@EnableKafka
public class KafkaConfig {

	@Autowired
	Environment env;

	@Value("spring.kafka.bootstrap-servers")
	private String bootStrapServers;

	@Value("spring.kafka.consumer.group-id")
	private String groupId;

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(3);
		factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}

	private ConsumerFactory<Integer, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	private Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty(bootStrapServers));
		props.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty(groupId));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");

		return props;
	}
}
