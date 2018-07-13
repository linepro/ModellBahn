package com.linepro.modellbahn.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SelectorsBuilder {

    public Map<String,Selector> build(Class<?> clazz, List<Class<? extends Annotation>> list) {
        
        Map<String,Selector> selectors = new HashMap<String,Selector>();

        for (Method getter : clazz.getMethods()) {
            for (Class<? extends Annotation> annotation : list) {
                if (getter.isAnnotationPresent(annotation)) {
                    try {
                        StringBuffer sb = new StringBuffer(getter.getName());
        
                        sb.replace(0,1,"s");
                                
                        String setterName = sb.toString();

                        Method setter = clazz.getMethod(setterName, getter.getReturnType());
        
                        sb.delete(0,3);
                        sb.replace(0,1,StringUtils.lowerCase(sb.substring(0,1)));

                        String name = sb.toString();

                        Selector selector = new Selector(name, getter, setter);

                        selectors.put(selector.getName(), selector);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return selectors;
    }
}
