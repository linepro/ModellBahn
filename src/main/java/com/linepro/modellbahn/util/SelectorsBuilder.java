package com.linepro.modellbahn.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * SelectorsBuilder.
 * Generates a map of selectors by reflection 
 * @author  $Author:$
 * @version $Id:$
 */
public class SelectorsBuilder {

    /** The logger. */
    protected final Logger logger;

    /**
     * Instantiates a new selectors builder.
     */
    public SelectorsBuilder() {
        logger = Logger.getLogger("ModellBahn");
    }

    /**
     * Builds a map of selectors by reflection.
     *
     * @param entityClass the entity class
     * @param annotations the annotations to scan for
     * @return the map of selectors
     */
    @SafeVarargs
    public final Map<String,Selector> build(Class<?> entityClass, Class<? extends Annotation>... annotations) {
        Map<String,Selector> selectors = new HashMap<String,Selector>();

        try {
            for (Method getter : entityClass.getMethods()) {
                for (Class<? extends Annotation> annotation : annotations) {
                    Annotation annotated = getter.getAnnotation(annotation);

                    if (annotated != null) {
                        Method setter = getSetter(getter);
                        
                        String name;
                        
                        if (annotated instanceof JsonGetter) {
                            name = ((JsonGetter) annotated).value();
                        } else {
                            name = getJpaName(getter.getName());
                        }

                        Selector selector = new Selector(name, getter, setter);

                        selectors.put(selector.getName(), selector);
                    }
              }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "SelectorsBuilder", e);
        }
        
        return selectors;
    }

    /**
     * Gets the jpa name.
     * E.g. for getName returns name.
     * @param getterName the getter name
     * @return the jpa name
     */
    protected String getJpaName(String getterName) {
        StringBuffer sb = new StringBuffer(getterName);
        sb.delete(0,3);
        sb.replace(0,1,StringUtils.lowerCase(sb.substring(0,1)));
        return sb.toString();
    }
    
    /**
     * Gets the setter corresponding to the getter.
     * @param getter the getter
     * @return the setter or null if none exists
     */
    protected Method getSetter(Method getter) {
        StringBuffer sb = new StringBuffer(getter.getName());
        
        sb.replace(0,1,"s");
                
        String setterName = sb.toString();
        
        try {
            return getter.getDeclaringClass().getMethod(setterName, getter.getReturnType());
        } catch (NoSuchMethodException e) {
        }

        return null;
    }
}
