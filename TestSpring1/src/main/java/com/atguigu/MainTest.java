package com.atguigu;


import com.atguigu.bean.Color;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.config.MainConfig2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

public strictfp class MainTest {

	public static void main(String[]  args)
	{
		ConcurrentHashMap
		Thread t;

		System.out.println(MainTest.class.getResource("/").toString());

	//	c.getResource()

	}
	static int test()
	{
		try{
			Byte var1 = 126;
			java.lang.String str=null;

			Byte var2 = 127;
			Byte var3 = 6;   //第7行
			var3++;
		return  4;}
		catch(Exception e) {}
		finally{

return 5;
		}
	}
	
	@SuppressWarnings("resource")
	public static void main44(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//		Person bean = (Person) applicationContext.getBean("person");
//		System.out.println(bean);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		//用ID
		Color bean =(Color) applicationContext.getBean("colorFactoryBean");
		System.out.println(bean);
		
		String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : namesForType) {
			System.out.println(name);
		}
	
	}

}
