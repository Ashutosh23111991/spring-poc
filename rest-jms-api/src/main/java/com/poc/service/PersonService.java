package com.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	JmsTemplate jmsTemplate;
	private static final String MESSAGE_QUEUE = "message_queue";

	public void getAllPersons(String id) {
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, id);
	}
}
