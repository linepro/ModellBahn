package com.linepro.modellbahn.persistence.impl;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * NamedItemConverter.
 * Converts from name to NamedItem.
 * @author  $Author:$
 * @version $Id:$
 */
class NamedItemConverter implements Converter {
    
    /** The persister. */
    private final IPersister<?> persister;
    
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
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (StringUtils.isNotBlank(value.toString())) {
                Class<?> entityClass = getPersister().getEntityClass();

                if (entityClass.isAssignableFrom(type)) {
                    IItem<?> entity = getPersister().findByKey(new NameKey(value.toString()), false);

                    if (entity != null) {
                        return (T) entity;
                    }

                    throw new IllegalArgumentException(value + ApiNames.DOES_NOT_EXIST);
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