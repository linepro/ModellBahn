package com.linepro.modellbahn.persistence.impl;

import java.util.Collection;

public interface ILazyCollectionPopulator {

    void populate(Collection<?> collection) throws Exception;

}
