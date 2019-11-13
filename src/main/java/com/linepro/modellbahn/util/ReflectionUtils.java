package com.linepro.modellbahn.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {

    /**
     * Get the first ParameterizedTypes to be find in the given class hierarchy.
     * @param clazz the class to check
     * @return array of ParameterizedTypes or empty arrays.
     */
    public static Class<?>[] getParameterizedTypes(Class<?> clazz) {

        if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
            return getType((ParameterizedType) clazz.getGenericSuperclass());
        }

        if (clazz.getSuperclass() != null) {
            return getParameterizedTypes(clazz.getSuperclass());
        }

        return new Class<?>[0];
    }
    
    private static Class<?>[] getType(ParameterizedType parameterizedType) {
    	List<Class<?>> types = new ArrayList<>();
        for (Type type : parameterizedType.getActualTypeArguments()) {
        	if (type instanceof Class) {
        		types.add((Class<?>) type);
        	}
        }
        return types.toArray(new Class[0]);
    }
}
