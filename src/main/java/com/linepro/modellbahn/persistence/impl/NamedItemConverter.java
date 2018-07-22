package com.linepro.modellbahn.persistence.impl;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.IPersister;

public class NamedItemConverter<I extends INamedItem> implements Converter {
    
    protected final IPersister<I> persister;
    
    public NamedItemConverter(IPersister<I> persister) {
        this.persister = persister;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            if (type.isAssignableFrom(persister.getEntityClass())) {
                String valueStr = value.toString();
                if (StringUtils.isNotBlank(valueStr)) {
                    I entity = persister.findByKey(valueStr);
                    
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