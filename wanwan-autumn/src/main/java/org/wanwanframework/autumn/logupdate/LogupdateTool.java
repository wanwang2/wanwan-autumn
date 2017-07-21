package org.wanwanframework.autumn.logupdate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wanwanframwork.file.Log;

public class LogupdateTool {

	public static String getMatch(String content, String regex) {
		if(content == null) {
			return null;
		}
		content = content.replace("[]", "");
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		if(m.find()) {
			return m.group();
		}
		return null;
	}
	
	public static String getMatchByRegexs(String content, String[] regex) {
		String match = content;
		for (String string : regex) {
			match = getMatch(match, string);
		}
		return match;
	}
	
	
	/**
	 * 获取方法名
	 * @param content
	 * @return
	 */
	public static String getMethod(String content) {
		return getMatchByRegexs(content, new String[]{
				PatternConfig.methodSignaturePattern,
				PatternConfig.methodName,
				PatternConfig.namePattern});
	}
	
	public static String getKey(String content, String regex) {
		return getMatchByRegexs(content, new String[]{regex, "\"\\S+\""});
	}
	
	public static String getValue(String content) {
		return getMatchByRegexs(content, new String[]{"\\,\\s*\\S+\\s*map", "\"\\S+\""});
	}
	
	public static void main(String[] args) {
		Log.log(getMatch("int[] a", PatternConfig.typePattern));
		Log.log(getMatch("public static String getMatch(String content, String regex) { ", PatternConfig.methodSignaturePattern));
		Log.log(getMethod("public static String getMatch(String content, String regex) { "));
		Log.log(getMethod("public int[] a(int b)"));
		
		Log.log("field:" + getKey("put(\"vv\", \".....................\", map);", "put\\(\\S+\\,"));
		Log.log("value:" + getValue("put(\"vv\", \".....................\", map);"));
	}
}
