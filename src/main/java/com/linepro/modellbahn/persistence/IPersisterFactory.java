package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;

/**
 * A factory for creating IPersister objects.
 * @author   $Author$
 * @version  $Id$
 */
public interface IPersisterFactory {
    
    /**
     * Creates a new IPersister object.
     *
     * @param <E> the element type
     * @param clazz the clazz
     * @return the i persister< e>
     * @throws ClassNotFoundException 
     */
    <E extends IItem<?>> IPersister<E> createPersister(Class<E> clazz);
}
