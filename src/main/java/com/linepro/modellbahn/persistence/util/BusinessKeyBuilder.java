package com.linepro.modellbahn.persistence.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class BusinessKeyBuilder {

    public List<String> build(Class<?> entityClass) {

        List<String> keys = new ArrayList<String>();

        for (Method getter : entityClass.getMethods()) {
            System.out.println(getter.getName());
            if (getter.isAnnotationPresent(BusinessKey.class)) {
                StringBuffer sb = new StringBuffer(getter.getName());
                sb.delete(0,3);
                sb.replace(0,1,StringUtils.lowerCase(sb.substring(0,1)));

                keys.add(sb.toString());
            }
        }

        return keys;
    }
}
