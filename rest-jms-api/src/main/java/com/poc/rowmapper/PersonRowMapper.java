package com.poc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;

import com.poc.jms.model.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	@Cacheable("person")
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setEmpId(rs.getInt("id"));
		person.setEmail(rs.getString("EMAIL"));
		person.setFirstname(rs.getString("FIRST_NAME"));
		person.setJoinedDate(rs.getDate("JOINED_DATE"));
		person.setLastName(rs.getString("LAST_NAME"));
		return person;
	}

}
