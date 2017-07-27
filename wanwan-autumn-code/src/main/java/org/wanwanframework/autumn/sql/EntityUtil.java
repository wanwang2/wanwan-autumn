package org.wanwanframework.autumn.sql;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.wanwanframwork.file.FileReader;

/**
 * 提供从sql-create 语句到entity之间的转换
 * @author coco
 *
 */
public class EntityUtil {

	public static Map<String, String> typeMap;
	public static Map<String, String> commentMap;
	
	public static void init() {
		String type = FileReader.load("./src/main/resources/sql/entityType.txt");
		typeMap = getType(type);
	}
	
	public static String getCreate(String sql) {
		int start 	= sql.indexOf("(");
		int end	  	= sql.lastIndexOf(")");
		String sub 	= sql.substring(start + 1, end);
		return sub;
	}
	
	public static Map<String, String> getComment(String comment) {
		comment = comment.replaceAll("comment on column \\w+.", "");
		comment = comment.replace("\r\n", "");
		String[] fields = comment.split(";");
		String[] keyValue;
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < fields.length; i++) {
			keyValue = fields[i].split("is");
			map.put(keyValue[0].trim(), keyValue[1].trim());
		}
		return map;
	}
	
	public static Map<String, String> getType(String content) {
		Map<String, String> map = new HashMap<String, String>();
		String[] types = content.split("\r\n");
		for (int i = 0; i < types.length; i++) {
			String[] keyValue = types[i].split("\\s+");
			if(keyValue != null && keyValue.length == 2){
				map.put(keyValue[0].trim(), keyValue[1].trim());	
			}
		}
		return map;
	}
	
	public static Map<String, String> getField(String sql) {
		sql = sql.replace("\r\n", "");
		String[] fields = sql.split(",");
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(int i = 0; i < fields.length; i++) {
			String fieldString = fields[i].trim();
			String[] keyValue = fieldString.split("\\s+");
			if(keyValue != null && keyValue.length == 2){
				map.put(keyValue[0].trim(), toType(keyValue[1].trim()));	
			}
		}
		return map;
	}
	
	public static String toType(String key) {
		String value = null;
		for(String mapKey : typeMap.keySet()) {
			if(key.indexOf(mapKey) >= 0){
				value = typeMap.get(mapKey);
			}
		}
		return value;
	}
	
//	public static void printMap(Map<String, String> map, Map<String, String> comment) {
//		Set<String> keySet = map.keySet();
//		for (String key:keySet) {
//			String value = map.get(key);
//			String commentValue = comment.get(key);
//			//printSingle(key, value, commentValue);
//		}
//	}

}
