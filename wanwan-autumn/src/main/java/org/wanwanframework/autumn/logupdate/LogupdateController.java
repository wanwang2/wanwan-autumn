package org.wanwanframework.autumn.logupdate;

import org.wanwanframwork.file.core.FileController;

public class LogupdateController extends FileController<String>{

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		super.process();
	}
	
	public static void main(String[] args) {
		LogupdateController.call(new LogupdateController());
		
	}
}
