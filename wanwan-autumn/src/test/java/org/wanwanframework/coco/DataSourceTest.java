package org.wanwanframework.coco;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/context-datasource.xml" })
public class DataSourceTest {

	@Autowired
	private DruidDataSource druidDataSource;
	
	@Test
	public void testDataSource() {
		assertTrue(druidDataSource != null);
		System.out.println("druidDataSource:" + druidDataSource);
	}
}
