package com.neosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.neosoft.model.Employee;

@Service
public class TestService {
	
	@Value("${app.topic.name}")
	private String topicName;
	@Value("${app.topic.name2}")
	private String topicName2;
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	public String saveList(String msg) {
		template.send(topicName, msg);
		return "publish successfully";
	}
	public String saveEmployeeList(Employee e) {
		template.send(topicName2, e);
		return "publish successfully";
	}

}
