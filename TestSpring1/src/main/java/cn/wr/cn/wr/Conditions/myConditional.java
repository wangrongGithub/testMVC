package cn.wr.cn.wr.Conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class myConditional implements Condition {
    //ConditionContext使用的上下文环境
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment  env=conditionContext.getEnvironment();

        String osName=env.getProperty("os.name");
        if(osName.toLowerCase().contains("window"))
        {
            System.out.println(osName);
            return true;
        }


        return false;
    }
}
