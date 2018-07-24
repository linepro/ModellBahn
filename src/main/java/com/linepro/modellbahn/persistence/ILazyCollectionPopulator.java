package com.linepro.modellbahn.persistence;

import java.util.Collection;

public interface ILazyCollectionPopulator {

    void populate(Collection<?> collection) throws Exception;

}
