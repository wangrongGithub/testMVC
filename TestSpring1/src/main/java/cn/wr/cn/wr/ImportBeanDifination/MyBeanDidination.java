package cn.wr.cn.wr.ImportBeanDifination;

import cn.wr.cn.wr.instance.Red;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyBeanDidination implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry)
    {
        String[] strs=beanDefinitionRegistry.getBeanDefinitionNames();
        for(String str:strs)
        {
            System.out.println(str);










            BeanDefinition beanDefinition= beanDefinitionRegistry.getBeanDefinition(str);
            System.out.println("beanDefinition--->"+beanDefinition);
        }
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Red.class);
        beanDefinitionRegistry.registerBeanDefinition("wrRed",rootBeanDefinition);


    }
}
