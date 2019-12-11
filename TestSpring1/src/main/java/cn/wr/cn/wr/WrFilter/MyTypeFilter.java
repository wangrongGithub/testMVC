package cn.wr.cn.wr.WrFilter;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException
    {
        //获取当前扫描的类信息
        ClassMetadata classMetadata= metadataReader.getClassMetadata();

        //获取当前扫描的类的注解的信息
        AnnotationMetadata annotationMetadata=metadataReader.getAnnotationMetadata();
        classMetadata.getClassName();
        if( classMetadata.getClassName().contains("wr"))
        {
            System.out.println(classMetadata.getClassName());
            return true;
        }






        return false;
    }
}
