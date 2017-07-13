package org.wanwanframework.autumn;

import org.wanwanframework.autumn.logupdate.LogupdateController;
import org.wanwanframwork.file.Log;

public class LogupdateTestController extends LogupdateController{

	@Override
	protected void process() {
		getContent();
	}
	
	public void getContent() {
		try {
			@SuppressWarnings("unused")
			int i = 9 /0;
		} catch (Exception e) {
			Log.error("error", e);
		}
	}
	
	public void getContent2() {
		try {
			
		} catch (Exception e) {
			Log.error("error", e);
		}
	}
	
	public static void main(String[] args) {
		LogupdateTestController.call(new LogupdateTestController());
	}
}
