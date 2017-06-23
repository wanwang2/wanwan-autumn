package org.wanwanframework.autumn;

import java.io.File;

public class AutumnTool {

	/**
	 * 获取Files
	 * @param dir
	 * @param type
	 * @return
	 */
	public static File[] readDirByType(File dir, String type) {
		File[] files = dir.listFiles(file -> {
			return file.getName().endsWith(type)? true:false;
		});
		return files;
	}
	
	public static File[] readDirByXml(File dir) {
		return readDirByType(dir, ".xml");
	}
	
	/**
	 * 获取code
	 * @param name
	 * @return
	 */
	public static String getCode(String name) {
		return name.substring(0, name.indexOf("."));
	}
}
