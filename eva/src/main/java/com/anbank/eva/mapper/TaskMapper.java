package com.anbank.eva.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskMapper {
	
	// 获得数据仓库最新一天的数据
	@Select("select count(1) from information_schema.tables")
	Integer doNothing();
	
}
