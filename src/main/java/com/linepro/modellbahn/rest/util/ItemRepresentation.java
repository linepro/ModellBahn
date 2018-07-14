package com.linepro.modellbahn.rest.util;

import com.linepro.modellbahn.model.IItem;

public class ItemRepresentation<E extends IItem> {

    protected int limit;

    protected int offset;

    protected int modelLimit;

    protected final E item;

    protected String href;

    public ItemRepresentation(E item) {
        this.item = item;
    }
    
    public E getItem() {
        return item;
    }
}
