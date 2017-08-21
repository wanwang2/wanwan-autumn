package org.wanwanframework.autumn.sql;

import java.util.LinkedHashMap;
import java.util.Map;

import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;

public class SqlTool {

	public static void main(String[] args) {
		String content = FileUtil.readFile("./src/main/resources/sql/notification_template.sql.txt");
		Log.log(content);
		String[] contents = content.split("\r\n\\(|\\)\r\n");
		Map map = getTypeMap(contents[1]);
		Log.log(map);
		String[] comments = content.split("-- Add comments to the columns|-- Create/Recreate");
		Map comment = getCommentMap(comments[1]);
		Log.log(comment);
		Sql sql = new Sql(map, comment);
		Log.log(sql.toLine());
	}
	
	public static Map<String, String> getTypeMap(String field) {
		String[] kv = field.split(",");
		Map<String, String> map = new LinkedHashMap<>();
		for (String string : kv) {
			setKey(string, map, "\\s+");
		}
		return map;
	}
	
	public static Map<String, String> getCommentMap(String field) {
		String[] kv = field.replace("comment on column NOTIFICATION_TEMPLATE.", "")
				.replace("is", "").split(";");
		Map<String, String> map = new LinkedHashMap<>();
		for (String string : kv) {
			setKey(string, map, "\\s+");
		}
		return map;
	}
	
	public static void setKey(String keyValue, Map<String, String> map, String splite) {
		String[] lines = keyValue.trim().split(splite);
		if(lines != null && lines.length > 1) {
			map.put(lines[0].trim(), lines[1].trim());
		}
	}
}
