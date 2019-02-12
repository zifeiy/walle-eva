package com.anbank.eva.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anbank.eva.mapper.TaskMapper;
import com.anbank.eva.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public void sayHello() {
		System.out.println("hello " + new Date());
	}

	@Override
	public void sayByebye() {
		System.out.println("byebye " + new Date());
	}
	
	

}
