package com.linepro.modellbahn.util;

import com.linepro.modellbahn.rest.util.ApiNames;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Method;

/**
 * Selector.
 * Maps getter and setter to a parameter name. 
 * @author  $Author:$
 * @version $Id:$
 */
public class Selector {

    /** The name. */
    private final String name;

    /** The getter. */
    private final Method getter;

    /** The setter. */
    private final Method setter;

    private final boolean collection;

    private final Class<?> type;

    /**
     * Instantiates a new selector.
     *
     * @param name the name
     * @param getter the getter
     * @param setter the setter
     */
    public Selector(String name, Class<?> type, Method getter, Method setter, boolean collection) {
        this.name = name;
        this.type = type;
        this.getter = getter;
        this.setter = setter;
        this.collection = collection;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * Gets the getter.
     *
     * @return the getter
     */
    public Method getGetter() {
        return getter;
    }

    /**
     * Gets the setter.
     *
     * @return the setter
     */
    public Method getSetter() {
        return setter;
    }

    public boolean isCollection() {
        return collection;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Selector other = (Selector) obj;

        return new EqualsBuilder().append(getName(), other.getName()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.NAMEN, getName())
                .append("getter", getGetter().getName())
                .append("setter", getSetter().getName())
                .append("collection", isCollection())
                .toString();
    }
}
