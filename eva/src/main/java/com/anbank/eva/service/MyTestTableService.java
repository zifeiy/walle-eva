package com.anbank.eva.service;

import java.util.List;

import com.anbank.eva.po.MyTestTable;

public interface MyTestTableService {
	
	List<MyTestTable> getMyTestTable(String name, Integer age, String address, Integer limit, Integer offset);
}
