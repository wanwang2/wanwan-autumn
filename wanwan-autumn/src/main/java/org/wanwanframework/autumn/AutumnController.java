package org.wanwanframework.autumn;

import java.util.Map;

import org.wanwanframework.file.map.LineTool;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.core.FileController;

public class AutumnController extends FileController<Map<String, String>>{

	public AutumnController() {
		core = LineTool.getLine("./src/main/resources/autumn/core.txt");
		Log.log(core);
	}
	
	public static void main(String[] args) {
		AutumnController.call(new AutumnController());
	}
}
