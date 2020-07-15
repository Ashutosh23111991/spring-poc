package com.poc.batch.config;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.poc.jms.model.Person;
import com.poc.rowmapper.PersonRowMapper;

@Configuration
@EnableBatchProcessing
public class DatabaseCursorJobConfig {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseCursorJobConfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	@StepScope
	public JdbcCursorItemReader<Person> itemReader(@Value("#{jobParameters}") Map<String, JobParameter> jobParameters) {
		JdbcCursorItemReader<Person> cursorItemReader = new JdbcCursorItemReader<>();
		String id = jobParameters.get("id").toString();
		logger.info("Reading Person details from DB with person id " + id);
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT ID, EMAIL, FIRST_NAME, JOINED_DATE, LAST_NAME FROM PERSON where ID =" + id);
		cursorItemReader.setRowMapper(new PersonRowMapper());
		return cursorItemReader;
	}

	@Bean
	public ItemWriter<Person> writer() {
		ItemWriter<Person> writer = new ItemWriter<Person>() {

			@Override
			public void write(List<? extends Person> items) throws Exception {
			}
		};
		return writer;

	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(2).reader(itemReader(null)).writer(writer())
				.build();
	}

	@Bean
	public Job exportJob() {
		return jobBuilderFactory.get("exportJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
}
