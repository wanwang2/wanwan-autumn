package org.wanwanframework.autumn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.wanwanframework.file.map.LineTool;
import org.wanwanframework.file.map.ReplaceUtil;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.core.FileController;

public class AutumnController extends FileController<Map<String, String>>{

	private Map<String, String> codeConfig;
	private String templateCode;
	private int currentIndex;
	private FilterLine line = null;
	
	public AutumnController() {
		core = LineTool.getLine("./src/main/resources/autumn/core.txt");
		codeConfig = LineTool.getLine("./src/main/resources/autumn/config.txt");
		Log.log(core);
		Log.log(codeConfig);
	}
	
	@Override
	protected void process() {
		String dir = "./src/main/resources/autumn";
		Arrays.asList(AutumnTool.readDirByXml(new File(dir))).stream().forEach(xml -> {
			currentIndex = 0;
			templateCode = AutumnTool.getCode(xml.getName());
			content = FileUtil.readFile(xml, "utf-8");
			if(codeConfig.containsKey(templateCode)) {
				String[] fields = codeConfig.get(templateCode).trim().replace("\r\n","").split("、");
				processFilter(content, fields, dir, processLine(fields));
			}
		});
	}
	
	protected List<FilterLine> processLine(String[] fields) {
		List<FilterLine> list = new ArrayList<>();
		
		Arrays.asList(fields).stream().forEach(field -> {
			if(field.contains("&")) {
				String[] p = field.split("&");
				line = new FilterLine();
				list.add(line);
				line.positions = p;
			} else {
				line.fields.add(field);
			}
		});
		
		return list;
	}
	
	protected void processFilter(String content, String[] list, String dir, List<FilterLine> line) {
		String mask = core.get("maskDown");
		for (FilterLine filterLine : line) {
			content = filterList(content, filterLine, mask);
		}

		try {
			FileUtil.createFile(dir + "/filter/" + templateCode + ".txt", content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.content = content;
	}
	
	private String filterList(String content, FilterLine line, String mask) {
		for (String f: line.fields) {
			content = filter(content, f, mask, line.positions[1]);
		}
		return content;
	}
	
	private String filter(String content, String field, String mask, String end) {
		if(content.contains(field)) {
			String replaced = field + "\" maskDown=\"" + mask;
			currentIndex = ReplaceUtil.getIndex(currentIndex, content, field);
			int endIndex = ReplaceUtil.getIndex(currentIndex, content, end);
			content = ReplaceUtil.replaceByIndex(currentIndex, endIndex, content, field, replaced);
		}
		return content;
	}
	
	public static void main(String[] args) {
		AutumnController.call(new AutumnController());
	}
}
