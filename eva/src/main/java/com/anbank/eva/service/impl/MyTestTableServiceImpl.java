package com.anbank.eva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anbank.eva.mapper.MyTestTableMapper;
import com.anbank.eva.po.MyTestTable;
import com.anbank.eva.service.MyTestTableService;

@Service
@Transactional
public class MyTestTableServiceImpl implements MyTestTableService {

	@Autowired
	private MyTestTableMapper myTestTableMapper;
	
	@Override
	public List<MyTestTable> getMyTestTable(String name, Integer age, String address, Integer limit, Integer offset) {
		return this.myTestTableMapper.getMyTestTable(name, age, address, limit, offset);
	}
	
}
