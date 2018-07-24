package com.linepro.modellbahn.persistence;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.INamedItem;

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
     */
    <E extends IItem> IPersister<E> createItemPersister(Class<E> clazz);

    /**
     * Creates a new IPersister object.
     *
     * @param <E> the element type
     * @param clazz the clazz
     * @return the i persister< e>
     */
    <E extends INamedItem> IPersister<E> createNamedItemPersister(Class<E> clazz);
}
