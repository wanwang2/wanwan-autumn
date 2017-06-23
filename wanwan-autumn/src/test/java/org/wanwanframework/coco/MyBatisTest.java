package org.wanwanframework.coco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/context-mybatis.xml" })
public class MyBatisTest {
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	
	@Test
	public void testSession() throws Exception {
		System.out.println("sqlSessionFactoryBean:" + sqlSessionFactoryBean);
	}
}
