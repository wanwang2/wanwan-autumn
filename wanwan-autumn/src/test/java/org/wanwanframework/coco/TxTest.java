package org.wanwanframework.coco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/context-tx.xml" })
public class TxTest {

	@Autowired
	DataSourceTransactionManager DataSourceTransactionManager;
	
	@Test
	public void testTx() {
		System.out.println("DataSourceTransactionManager:" + DataSourceTransactionManager);
	}
}
