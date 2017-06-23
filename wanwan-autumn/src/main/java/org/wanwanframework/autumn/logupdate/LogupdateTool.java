package org.wanwanframework.autumn.logupdate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wanwanframwork.file.Log;

public class LogupdateTool {

	public static String getMatch(String content, String regex) {
		if(content == null) {
			return null;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		if(m.find()) {
			return m.group();
		}
		return null;
	}
	
	/**
	 * 获取方法名
	 * @param content
	 * @return
	 */
	public static String getMethod(String content) {
		String match = getMatch(content, PatternConfig.methodSignaturePattern);
		String method = getMatch(match, PatternConfig.methodName);
		return getMatch(method, PatternConfig.namePattern);
	}
	
	public static void main(String[] args) {
		Log.log(getMatch("int a", PatternConfig.typePattern));
		Log.log(getMatch("public static String getMatch(String content, String regex) { ", PatternConfig.methodSignaturePattern));
		Log.log(getMethod("public static String getMatch(String content, String regex) { "));
		Log.log(getMethod("public int a(int b)"));
	}
}
