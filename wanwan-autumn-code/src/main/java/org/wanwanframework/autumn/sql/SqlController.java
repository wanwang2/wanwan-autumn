package org.wanwanframework.autumn.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.wanwanframework.file.config.ConfigController;
import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.core.FileController;
import org.wanwanframwork.file.util.NameUtil;

public class SqlController extends FileController<String>{
	
	protected ConfigController configController = new ConfigController("./src/main/resources/context/core.txt");
	protected static final String SPLIT_SQL_FIELD = "\r\n\\(|\\)\r\ntablespace";//分割字段
	protected String sqlField;
	
	public SqlController() {
		core = FileUtil.readFile("./src/main/resources/sql/notification.sql.txt", "utf-8");
	}
	
	@Override
	protected void process() {
		String[] par = core.split(SPLIT_SQL_FIELD);
		sqlField = par[1].replaceAll("not null", "");
		Log.log("sqlField:" + sqlField);
		EntityUtil.init();
		Map<String, String> map = EntityUtil.getField(sqlField);
		Log.log("map:" + map);
		processLine(map);
	}
	
	protected void processLine(Map<String, String> fieldMap) {
		String template = configController.getCore().get("@KeyValue.Template");
		List<String> fieldList = new ArrayList<>();
		fieldMap.forEach((k,v)->{
			k = StringUtils.lowerCase(k);
			k = NameUtil.replace(k, "_", "");
			fieldList.add(template.replace("@v", v).replace("@k", k));
		});
		Log.log("list:" + StringUtils.join(fieldList, "\r\n"));
	}
	
	public static void main(String[] args) {
		SqlController.start(SqlController.class);
	}
	
}
