package org.wanwanframework.autumn.code;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.wanwanframwork.file.core.FileArrayController;
import org.wanwanframwork.file.core.FileController;

public class CodeLoopController extends FileController<File[]>{

	protected FileArrayController fileArrayController = new FileArrayController(new File(
			"./src/main/resources/template"), "@");
	
	public CodeLoopController() {
		core = fileArrayController.getCore();
		content = StringUtils.join(core, ",");
	}
	
	@Override
	protected void process() {
		Arrays.asList(core).forEach(file->{
			CodeController codeController = new CodeController(file);
			codeController.init();
		});
	}
	
	public static void main(String[] args) {
		CodeLoopController.start(CodeLoopController.class);
	}
}
