package org.wanwanframework.coco;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartzTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-quartz.xml");
		SchedulerFactoryBean bean = context.getBean(SchedulerFactoryBean.class);
		bean.start();
	}
}
