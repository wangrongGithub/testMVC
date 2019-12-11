package cn.wr.Config;

import cn.wr.cn.wr.Conditions.myConditional;
import cn.wr.cn.wr.WrFilter.MyTypeFilter;
import cn.wr.cn.wr.instance.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@ComponentScan(value={"cn.wr.cn.wr.controller","cn.wr.cn.wr.service",},useDefaultFilters=false,
        //执行过程中先执行in,然后ex;
        excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})},
        includeFilters={@ComponentScan.Filter(type= FilterType.CUSTOM,classes={MyTypeFilter.class})}


        )

//value指定扫描的包
//excludeFilters:Filter[],指定排外的类
//includeFilters:Filter[],指定进行扫描的类

@Configuration
public class MainConfig1
{
    @Bean(name="hhh")
    @Conditional(value={myConditional.class})
    public Person Person2()
    {
        System.out.println("说明是eindowsa ");
        return  new Person("wee",12);
    }
}
