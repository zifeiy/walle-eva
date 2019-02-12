package com.anbank.eva.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbank.eva.assist.FileGenerateSucceedAssist;
import com.anbank.eva.assist.FileHelper;
import com.anbank.eva.config.EvaConfigure;
import com.anbank.eva.po.MyTestTable;
import com.anbank.eva.service.MyTestTableService;

@RestController
@RequestMapping("/my_test_table")
public class MyTestTableController {
	
	@Autowired
	private MyTestTableService myTestTableService;
	
	@RequestMapping("/result")
	public List<MyTestTable> selectMyTestTable(@RequestParam(value = "name", required = false) String name,
																					@RequestParam(value = "age", required = false) Integer age,
																					@RequestParam(value = "address", required = false) String address,
																					@RequestParam(value = "pageIndex", required = true) Integer pageIndex) {
		if (address != null) {
			address = "%" + address + "%";
		}
		int limit = 16;
		int offset = (pageIndex-1) * 16;
		return this.myTestTableService.getMyTestTable(name, age, address, limit, offset);
	}
	
	@RequestMapping("/download")
	public void downloadMyTestTable(@RequestParam(value = "name", required = false) String name,
																					@RequestParam(value = "age", required = false) Integer age,
																					@RequestParam(value = "address", required = false) String address,
																					HttpServletResponse response) {
		if (address != null) address = "%" + address + "%";
		
		Date date = new Date();
		String dateString = String.format("%d%02d%02d%02d%02d%02d%.0f", 1900 + date.getYear(), date.getMonth() + 1,
				date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds(), Math.random() * 1e6);
		String reportName = "测试表格";
		String filepath = EvaConfigure.PUBLIC_FILE_PATH + File.separator + reportName + dateString + ".csv";
		File file = new File(filepath);
		
		try {
			response.setHeader("Content-type", "textml;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(filepath);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] fileHeader = { "编号", "姓名", "年龄", "地址" };
		
		try {
			FileHelper.writeFile(file, "");
			FileHelper.appendCSV(file, fileHeader);
			List<MyTestTable> list = this.myTestTableService.getMyTestTable(reportName, age, address, null, null);
			for (MyTestTable ele : list) {
				List<String> record = new ArrayList<String>();
				record.add(ele.getId().toString());
				record.add(ele.getName());
				record.add(ele.getAge() != null ? ele.getAge().toString() : null);
				record.add(ele.getAddress());
				FileHelper.appendCSV(file, record);
			}
			FileGenerateSucceedAssist.generateOkFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
