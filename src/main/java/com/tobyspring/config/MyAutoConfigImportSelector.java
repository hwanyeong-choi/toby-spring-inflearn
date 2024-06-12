package com.tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.StreamSupport;

/*
    DeferredImportSelector을 구현하여 config class의
    이름을 selectImports 메소드를 통해 Sring배열로 리턴합니다.
 */

/*
    classLoader를 Spring Container로 부터 주입받아서 사용할 수 있습니다.
    지금은 생성자로 주입받았지만 BeanClassLoaderAware 인터페이스를 구현하여 주입받을
    수도 있습니다.
 */
public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /*
        구성정보로 사용될 후보 클래스들을 불러오는 작업을 합니다.
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        /*
            ImportCandidates.load를 통해 파일로부터 구성정보를 읽어낼때
            META-INF/spring/%s.imports 경로의 파일을 확인하여 가져옵니다.
         */
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
    }

}
