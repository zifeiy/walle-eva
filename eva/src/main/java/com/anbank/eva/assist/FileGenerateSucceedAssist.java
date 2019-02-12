package com.anbank.eva.assist;

import java.io.File;
import java.io.IOException;

public class FileGenerateSucceedAssist {
	public static void generateOkFile(File file) {
		if (file == null) return;
		String filepath = file.getAbsolutePath();
		File okFile = new File(filepath + ".ok");
		try {
			okFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
