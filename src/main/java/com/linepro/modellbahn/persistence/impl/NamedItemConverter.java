package com.linepro.modellbahn.persistence.impl;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IPersister;

/**
 * NamedItemConverter.
 * Converts from name to NamedItem.
 * @author  $Author:$
 * @version $Id:$
 *
 * @param <I> the generic type
 */
public class NamedItemConverter implements Converter {
    
    /** The persister. */
    protected final IPersister<?> persister;
    
    /**
     * Instantiates a new named item converter.
     *
     * @param persister the persister
     */
    public NamedItemConverter(IPersister<?> persister) {
        this.persister = persister;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <E> E convert(Class<E> type, Object value) {
        try {
            if (StringUtils.isNotBlank(value.toString())) {
                Class<? extends IItem<?>> entityClass = getPersister().getEntityClass();

                if (entityClass.isAssignableFrom(type)) {
                    IItem<?> entity = entityClass.newInstance();

                    entity = getPersister().findByKey(new NameKey(value.toString()), false);

                    if (entity != null) {
                        return (E) entity;
                    }

                    throw new IllegalArgumentException(value + " does not exist");
                }

                throw new IllegalArgumentException(type + " not supported");
            }
        } catch (Exception e) {
            throw new ConversionException(e);
        }
        
        return null;
    }

    private IPersister<?> getPersister() {
        return persister;
    }
}