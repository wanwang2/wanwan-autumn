package org.wanwanframework.autumn.logupdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.wanwanframework.file.config.ConfigController;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.core.FileController;

/**
 * 修饰log日志信息的Controller
 * @author coco
 *
 */
public class LogupdateController extends FileController<List<String>>{

	protected ConfigController configController = new ConfigController(LogupdateController.class);
	protected String method;
	protected String lineCache;
	
	public LogupdateController() {
		core = Arrays.asList(FileUtil.readFile("./src/test/java/org/wanwanframework/autumn/LogupdateTestController.java").split("\r\n"));
	}
	
	@Override
	protected void process() {
		List<String> list = new ArrayList<String>();
		core.forEach(line -> {
			
			String localMethod = processLine(line);
			if(localMethod != null) {
				method = localMethod;
			}
			if(method != null) {
				lineCache = line;
				configController.getCore().forEach((k, v)->{
					lineCache = processErrorLine(lineCache, method, k);
				});
				list.add(lineCache);
			} else {
				list.add(line);
			}
			
		});
		Log.log("\r\n" + StringUtils.join(list, "\r\n"));
	}
	
	protected String processLine(String line) {
		String method = LogupdateTool.getMethod(line);
		Log.log(method);
		return method;
	}
	
	protected String processErrorLine(String line, String word, String configKey) {
		String[] value = configController.getCore().get(configKey).split(",");
		Log.log(value);
		String errorRegex = value[0];
		if(line.contains(errorRegex)) {
			line = line.replace(errorRegex, value[1]);
			line = line.replace("@", word);
		}
		return line;
	}
	
	protected String processErrorLine(String line, String word) {
		String[] value = configController.getCore().get("put").split(",");
		Log.log(value);
		String errorRegex = value[0];
		if(line.contains(errorRegex)) {
			line = line.replace(errorRegex, value[1]);
			line = line.replace("@", word);
		}
		return line;
	}
	
	public static void main(String[] args) {
		LogupdateController.call(new LogupdateController());
		
	}
}
