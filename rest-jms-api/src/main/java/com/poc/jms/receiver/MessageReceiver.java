package com.poc.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.job.flow.FlowJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.poc.controller.PersonController;

@Component
public class MessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	private static final String MESSAGE_QUEUE = "message_queue";

	@Autowired
	ConfigurableApplicationContext context;

	@JmsListener(destination = MESSAGE_QUEUE)
	public void receiveMessage(String id) throws Exception {
		logger.info("Listener consuming message from JMS queue. person id " + id);
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		FlowJob flowJob = (FlowJob) context.getBean("exportJob");
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("id", id);
		jobParametersBuilder.addLong("date", System.currentTimeMillis());
		jobLauncher.run(flowJob, jobParametersBuilder.toJobParameters());

	}
}
