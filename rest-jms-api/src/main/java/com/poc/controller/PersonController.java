package com.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.poc.service.PersonService;

@RestController
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	@Autowired
	PersonService service;

	@RequestMapping("/persons/{id}")
	public void getAllPersons(@PathVariable("id") String id) {
		logger.info("Requested person id " + id);
		service.getAllPersons(id);
	}
}
