package com.anbank.eva.assist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileHelper {
    
    public static void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String line = null;   
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }  
        br.close();
    }
    
    public static void writeFile(File file, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);
        osw.flush();
        osw.close();
    }
    
    public static void appendFile(File file, String content) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(file, true), // true to append
                "UTF-8"
                );
        out.write(content);
        out.close();
    }
    
    public static void appendCSV(File file, String[] line) throws IOException {
    	String content = "";
    	for (String s : line) {
    		if (s != null)
    			s = s.replaceAll("\"", "\"\"");
    		content += (s == null ? "," : ",\"" + s.trim() + "\"");
    	}
    	if (content.length() > 0)
    		content = content.substring(1) + "\r\n";
        OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(file, true), // true to append
                "UTF-8"
                );
        out.write(content);
        out.close();
    }
    
    public static void appendCSV(File file, List<String> lineList) throws IOException {
    	String[] line = new String[lineList.size()];
    	for (int i = 0; i < lineList.size(); i ++) {
    		line[i] = lineList.get(i);
    	}
    	appendCSV(file, line);
    }
    
    // main for test
    public static void main(String[] args) {
//    	String s = "\"\"nihao\"h\"k\""; 
//    	s = s.replaceAll("\"", "\"\"");
//    	System.out.println(s);
    }
}
