package com.neosoft.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.neosoft.model.Employee;

@Service
public class MessageService {
	
	private Set<Object> li=new HashSet<>();
	

	@KafkaListener(topics = "${app.topic.name}",groupId = "Grp1")
	public void saveMessage(String message) {

		li.add(message);
		
	}
	@KafkaListener(topics = "${app.topic.name2}",groupId = "Grp2")
	public void saveEmployee(Employee e) {

		li.add(e);
		
	}
	
	public Set<Object> getAllMessage(){
		return li;
	}
}
