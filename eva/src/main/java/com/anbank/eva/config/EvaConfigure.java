package com.anbank.eva.config;

import java.io.File;

public class EvaConfigure {
	public static String PUBLIC_FILE_PATH = "D:\\eva\\public_resources";
	
	public static void initConfigure() {
		File publicFileDir = new File(PUBLIC_FILE_PATH);
		if (publicFileDir.exists() == false) {
			publicFileDir.mkdirs();
		}
	}
}
