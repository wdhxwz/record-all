package com.krista.springframework.v4x.ioc.test.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * MyImportSelector
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @date 2018/12/7 17:43
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 返回需要导入的类的完全限定名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Director.class.getName()};
    }
}
