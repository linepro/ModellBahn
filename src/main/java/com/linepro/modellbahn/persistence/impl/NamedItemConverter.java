package com.linepro.modellbahn.persistence.impl;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.IPersister;

/**
 * NamedItemConverter.
 * Converts from name to NamedItem.
 * @author  $Author:$
 * @version $Id:$
 *
 * @param <I> the generic type
 */
public class NamedItemConverter<I extends INamedItem> implements Converter {
    
    /** The persister. */
    protected final IPersister<I> persister;
    
    /**
     * Instantiates a new named item converter.
     *
     * @param persister the persister
     */
    public NamedItemConverter(IPersister<I> persister) {
        this.persister = persister;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (type.isAssignableFrom(persister.getEntityClass())) {
                if (StringUtils.isNotBlank(value.toString())) {
                    I entity = (I) type.newInstance();

                    ((INamedItem) entity).setName(value.toString());

                    entity = persister.findByKey(entity, false);
                    
                    if (entity != null) {
                        return (T) entity;
                    }
    
                    throw new IllegalArgumentException(value + " does not exist");
                }
            }

            throw new IllegalArgumentException(type + " not supported");
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }
}