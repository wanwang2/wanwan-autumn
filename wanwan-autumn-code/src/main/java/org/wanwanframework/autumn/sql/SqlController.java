package org.wanwanframework.autumn.sql;

import org.wanwanframwork.file.FileUtil;
import org.wanwanframwork.file.Log;
import org.wanwanframwork.file.core.FileController;

public class SqlController extends FileController<String>{

	protected static final String SPLIT_SQL_FIELD = "\r\n\\(|\\)\r\ntablespace";//分割字段
	protected String sqlField;
	
	public SqlController() {
		core = FileUtil.readFile("./src/main/resources/sql/notification.sql.txt", "utf-8");
	}
	
	@Override
	protected void process() {
		String[] par = core.split(SPLIT_SQL_FIELD);
		sqlField = par[1];
		content = core;
		Log.log("sqlField:" + sqlField);
	}
	
	public static void main(String[] args) {
		SqlController.start(SqlController.class);
	}
	
}
