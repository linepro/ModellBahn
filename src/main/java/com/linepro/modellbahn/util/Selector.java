package com.linepro.modellbahn.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * Selector.
 * Maps getter and setter to a parameter name. 
 * @author  $Author:$
 * @version $Id:$
 */
public class Selector {

    /** The name. */
    protected final String name;

    /** The getter. */
    protected final Method getter;

    /** The setter. */
    protected final Method setter;

    protected final boolean collection;

    /**
     * Instantiates a new selector.
     *
     * @param name the name
     * @param getter the getter
     * @param setter the setter
     */
    public Selector(String name, Method getter, Method setter, boolean collection) {
        this.name = name;
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
                .append(ApiNames.NAME, getName())
                .append("getter", getGetter().getName())
                .append("setter", getSetter().getName())
                .append("collection", isCollection())
                .toString();
    }
}
