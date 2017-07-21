package org.wanwanframework.autumn;

import java.util.HashMap;
import java.util.Map;

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
			Map<String, String> map = new HashMap<String, String>();
			map.put("vv", ".....................");
			map.put("cc", ".....................");
			Log.log(map);
		} catch (Exception e) {
			Log.error("error", e);
		}
	}
	
	public void getContent2() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("vv", ".....................");
			map.put("cc", ".....................");
			Log.log(map);
		} catch (Exception e) {
			Log.error("error", e);
		}
	}
	
	public static void main(String[] args) {
		LogupdateTestController.call(new LogupdateTestController());
	}
}
