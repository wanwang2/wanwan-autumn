package org.wanwanframework.autumn.code;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.wanwanframework.file.config.ConfigController;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.core.FileController;

/**
 * context/core.txt
 */
public class CodeController extends FileController<List<String>>{

	protected ConfigController configController = new ConfigController("./src/main/resources/context/core.txt");
	protected File file = new File("./src/main/resources/template/@ModeDao.java.txt");
	
	public CodeController() {
		core = Arrays.asList(FileUtil.readFile(file, "utf-8").split("\r\n"));
	}
	
	public CodeController(File file) {
		this.file = file;
		core = Arrays.asList(FileUtil.readFile(file, "utf-8").split("\r\n"));
	}
	
	@Override
	protected void process() {
		String[] keys = configController.getCore().get("@Mode").split(",");
		Arrays.asList(keys).forEach(key ->{
			processLine(key);
		});
	}
	
	protected void processLine(String key) {
		List<String> lineList = new ArrayList<>();
		core.forEach(string->{
			string = string.replace("@Mode", key);
			lineList.add(string);
		});
		String lines = StringUtils.join(lineList, "\r\n");
		Log.log(lines);
		String path = file.getPath().replace("@Mode", key);
		FileUtil.createFile(path, lines);
	}
	
	public static void main(String[] args) {
		CodeController.start(CodeController.class);
	}
}
