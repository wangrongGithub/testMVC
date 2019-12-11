package cn.wr.Config;

import cn.wr.cn.wr.WrFilter.MyTypeFilter;
import cn.wr.cn.wr.instance.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*@ComponentScan(value="cn.wr",useDefaultFilters=false,
        //执行过程中先执行in,然后ex;
        excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})},
        includeFilters={@ComponentScan.Filter(type= FilterType.CUSTOM,classes={MyTypeFilter.class})}


        )

//value指定扫描的包
//excludeFilters:Filter[],指定排外的类
//includeFilters:Filter[],指定进行扫描的类

@Configuration*/
public class MainConfig
{
    @Bean
    public Person PersonWR()
    {
        return  new Person("wee",12);
    }
}
