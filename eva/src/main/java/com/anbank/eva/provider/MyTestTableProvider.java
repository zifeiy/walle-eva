package com.anbank.eva.provider;

public class MyTestTableProvider {
	
	public String select(String name, Integer age, String address, Integer limit, Integer offset) {
		String sql = "select * from my_test_table where 1=1";
		if (name != null) {
			sql += " and name=#{name}";
		}
		if (age != null) {
			sql += " and age=#{age}";
		}
		if (address != null) {
			sql += " and address like #{address}";
		}
		if (limit != null && offset != null) {
			sql += " limit #{limit} offset #{offset}";
		}
		return sql;
	}
}
