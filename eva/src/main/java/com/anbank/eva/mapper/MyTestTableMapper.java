package com.anbank.eva.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.anbank.eva.po.MyTestTable;
import com.anbank.eva.provider.MyTestTableProvider;

@Mapper
public interface MyTestTableMapper {

	@SelectProvider(method = "select", type = MyTestTableProvider.class)
	List<MyTestTable> getMyTestTable(@Param("name") String name, @Param("age") Integer age, @Param("address") String address, @Param("limit") Integer limit, @Param("offset") Integer offset);
	
}
