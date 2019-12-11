package cn.wr.TestMain;

import cn.wr.Config.MainConfig;
import cn.wr.Config.MainConfig1;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;

public class Test1 {



    public    void main2()
    {
        ApplicationContext context=new  ClassPathXmlApplicationContext("bean.xml");
        System.out.println(context.getBean("person"));
        //可以不需要给定注解啊
        context=new AnnotationConfigApplicationContext(MainConfig1.class);
        String[] strs= context.getBeanDefinitionNames();
        for(String str:strs)
            System.out.println("扫描的啊   " +str);
        System.out.println(context.getBean("hhh"));
        String str="Tomorrow is a not sunny day";
        System.out.println(str.split("is a not")[0]);
        System.out.println(str.split("is a not")[1]);
    }

    @Test
    public    void main1() throws UnsupportedEncodingException {
        ApplicationContext context=  context=new AnnotationConfigApplicationContext(MainConfig.class);
       // System.out.println(context.getBean("person"));
        //可以不需要给定注解啊

        String[] strs= context.getBeanDefinitionNames();
        for(String str:strs)
            //GBK转成UTF-8
        {/*
        *
        *
        *
        *
        *
        */
            String ss = new String("扫描的啊   ".getBytes("GBK"),"UTF-8");
            System.out.println(ss + str);
        }
        //System.out.println(context.getBean("Person2"));

    }
}
