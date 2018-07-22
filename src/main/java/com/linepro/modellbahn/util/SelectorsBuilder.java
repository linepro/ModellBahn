package com.linepro.modellbahn.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonGetter;

public class SelectorsBuilder {

    public Map<String,Selector> build(Class<?> entityClass, List<Class<? extends Annotation>> annotations) {
        
        Map<String,Selector> selectors = new HashMap<String,Selector>();

        try {
            for (Method getter : entityClass.getMethods()) {
                for (Class<? extends Annotation> annotation : annotations) {
                    Annotation annotated = getter.getAnnotation(annotation);

                    if (annotated != null) {
                        StringBuffer sb = new StringBuffer(getter.getName());
                        
                        sb.replace(0,1,"s");
                                
                        String setterName = sb.toString();

                        Method setter = entityClass.getMethod(setterName, getter.getReturnType());

                        String name;
                        
                        if (annotated instanceof JsonGetter) {
                            name = ((JsonGetter) annotated).value();
                        } else {
                            // For JPA we require the getter name without get and camel cased
                            sb.delete(0,3);
                            sb.replace(0,1,StringUtils.lowerCase(sb.substring(0,1)));
                            name = sb.toString();
                        }

                        Selector selector = new Selector(name, getter, setter);

                        selectors.put(selector.getName(), selector);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return selectors;
    }
}
