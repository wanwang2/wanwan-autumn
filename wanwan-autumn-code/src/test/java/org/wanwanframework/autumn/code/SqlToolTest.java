package org.wanwanframework.autumn.code;

import java.util.Map;

import org.junit.Test;
import org.wanwanframework.autumn.sql.Sql;
import org.wanwanframework.autumn.sql.SqlTool;
import org.wanwanframework.file.FileUtil;
import org.wanwanframework.file.Log;

public class SqlToolTest {

	@Test
	public void testSql() {
		String content = FileUtil.readFile("./src/main/resources/sql/user_info.sql.txt");
		Log.log(content);
		String[] contents = content.split("\r\n\\(|\\)\r\n");
		Map map = SqlTool.getTypeMap(contents[1]);
		Log.log(map);
		String[] comments = content.split("-- Add comments to the columns|-- Create/Recreate");
		Map comment = SqlTool.getCommentMap(comments[1], "comment on column USER_INFO.");
		Log.log(comment);
		Sql sql = new Sql(map, comment);
		Log.log(sql.toLine());
	}
}
