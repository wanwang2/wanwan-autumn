package org.wanwanframework.autumn.sql;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.wanwanframwork.file.util.NameUtil;

public class Sql {

	private Map<String, String> javaTypeMap;
	private Map<String, String> map = new LinkedHashMap<>();
	
	public Sql(Map<String, String> typeMap, Map<String, String> commentMap) {
		typeMap.forEach((k, v) -> {
			map.put(k, "private " + v + "//");
		});
		commentMap.forEach((k, v) -> {
			String value = map.get(k);
			map.put(k, value + v.replace("\r\n", " "));
		});
	}
	
	public String toString() {
		return map.toString();
	}
	
	public String toType(String content) {
		for(Map.Entry<String, String> entry : javaTypeMap.entrySet()) {
			if(content.contains(entry.getKey())) {
				return content.replace(entry.getKey(), entry.getValue().replaceAll("\\([\\d]*\\)", ""));
			}
		}
		return content;
	}
	
	public String toLine() {
		map.forEach((k, v) -> {
			String value = map.get(k);
			value = toType(value);
			String code = NameUtil.getEntityName(k);
			map.put(k, value.replace("//", " " + code + ";//"));
		});
		return StringUtils.join(map.values(), "\r\n");
	}
}
