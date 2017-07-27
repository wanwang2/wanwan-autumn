package org.wanwanframework.autumn.code;

import java.io.File;

import org.wanwanframwork.file.Log;

public class CodeControllerTest {

	public static void main(String[] args) {
		File file = new File("./");
		String[] files = file.list();
		Log.log(files);
		Log.log("path:" + System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.compiler"));

	}
}
