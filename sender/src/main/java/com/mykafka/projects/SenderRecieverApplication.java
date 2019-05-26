package com.mykafka.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SenderRecieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenderRecieverApplication.class, args);
	}

}

