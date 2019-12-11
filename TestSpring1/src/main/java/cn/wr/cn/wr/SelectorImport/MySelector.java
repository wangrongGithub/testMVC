package cn.wr.cn.wr.SelectorImport;

import cn.wr.cn.wr.instance.Yellow;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MySelector implements ImportSelector {
    /*
    * 当前类标注的注解信息都可以拿到
    * 当前类标注的注解信息都可以拿到
    *
    * */
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("selectImports--->"+"Yellow");
        return new String[]{"cn.wr.cn.wr.instance.Yellow"};
    }
}
