package com.linepro.modellbahn.persistence.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.model.util.AbstractNamedItem;
import com.linepro.modellbahn.rest.util.ApiMessages;
import com.linepro.modellbahn.util.ReflectionUtils;

/**
 * NamedItemConverter.
 * Converts from name to NamedItem.
 * @author  $Author:$
 * @version $Id:$
 */
class NamedItemConverter<E extends INamedItem,T extends AbstractNamedItem<?>> implements Converter {
    
    /** The persister. */
    private final INamedItemRepository<T> persister;
    
    private final Class<E> persistentClass;
    
    /**
     * Instantiates a new named item converter.
     *
     * @param persister the persister
     */
    @SuppressWarnings("unchecked")
    public NamedItemConverter(INamedItemRepository<T> persister) {
        this.persister = persister;
        
        this.persistentClass = (Class<E>) ReflectionUtils.getParameterizedTypes(getClass())[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E convert(Class<E> type, Object value) {
        try {
            if (StringUtils.isNotBlank(value.toString())) {
                if (persistentClass.isAssignableFrom(type)) {
                    T entity = persister.findByName(value.toString());

                    if (entity != null) {
                        return (E) entity;
                    }

                    throw new IllegalArgumentException(getMessage(ApiMessages.DOES_NOT_EXIST, value));
                }

                throw new IllegalArgumentException(getMessage(ApiMessages.NOT_SUPPORTED, type));
            }
        } catch (Exception e) {
            throw new ConversionException(e);
        }
        
        return null;
    }

    protected String getMessage(String messsgeCode, Object... args) {
        return String.format(messsgeCode, args);
    }
}