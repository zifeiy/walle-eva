package com.anbank.eva.assist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Csv2ExcelAssist {
	
	public static String transferCsv2Excel(String csvFilePath) {
		File csvFile = new File(csvFilePath);
		return transferCsv2Excel(csvFile);
	}
	
	public static String transferCsv2Excel(File csvFile) {
		String csvFilePath = csvFile.getAbsolutePath();
		String xlsFilePath = csvFilePath.substring(0, csvFilePath.length()-4) + ".xls";
		File xlsFile = new File(xlsFilePath);
		if (transferCsv2Excel(csvFile, xlsFile) == false) {
			return csvFilePath;
		}
		else {
			return xlsFilePath;
		}
	}
	
	public static boolean transferCsv2Excel(File csvFile, File xlsFile) {
		if (csvFile != null && csvFile != null && csvFile.exists() == true && csvFile.length() <= 10 * 1024 * 1024) {	// 如果CSV文件存在并且大小不超过 10 MB
			try {
				WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
				WritableSheet sheet = workbook.createSheet("sheet1", 0);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
		        String line = null;
		        int rowId = 0;
		        while ((line = br.readLine()) != null) {
		            boolean quoted = false;
		            List<String> rowList = new ArrayList<String>();
		            String tmpS = "";
		            for (int i = 0; i < line.length(); i ++) {
		            	char c = line.charAt(i);
		            	if (c == '\"') quoted = !quoted;
		            	else if (c == ',') {
		            		if (quoted == true) {
		            			tmpS += c;
		            		} else {
		            			rowList.add(tmpS);
		            			tmpS = "";
		            		}
		            	}
		            	else tmpS += c;
		            }
		            if (tmpS.length() > 0) rowList.add(tmpS);
		            		
	            	for (int colId = 0; colId < rowList.size(); colId ++) {
	            		sheet.addCell(new Label(colId, rowId, rowList.get(colId)));
	            	}
	            	rowId ++;
		            
		        }
		        br.close();
		        
		        workbook.write();
		        workbook.close();
			} catch (Exception e) {
				return false;
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		File xlsFile = new File("D:\\test.txt");
		System.out.println(xlsFile.length());
	      // 创建一个工作簿
//	      WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
//	      // 创建一个工作表
//	      WritableSheet sheet = workbook.createSheet("sheet1", 0);
//	      for (int row = 0; row < 10; row++)
//	      {
//	         for (int col = 0; col < 10; col++)
//	         {
//	            // 向工作表中添加数据
//	            sheet.addCell(new Label(col, row, "zifeiy" + row + "," + col));
//	         }
//	      }
//	      workbook.write();
//	      workbook.close();
	}
	
}
