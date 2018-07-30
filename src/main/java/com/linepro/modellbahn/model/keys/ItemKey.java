package com.linepro.modellbahn.model.keys;

import java.util.Collection;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.util.Selector;

public class ItemKey extends BaseKey {

    protected final IItem<?> item;

    protected final Collection<Selector> businessKeys;
    
    public ItemKey(final IItem<?> item, final Collection<Selector> businessKeys) {
        this.item = item;
        this.businessKeys = businessKeys;
    }

    public IItem<?> getItem() {
        return item;
    }

    public Collection<Selector> getBusinessKeys() {
        return businessKeys;
    }

    @Override
    public void addCriteria(Query query) throws Exception {
        for (Selector businessKey : getBusinessKeys()) {
            Object value = businessKey.getGetter().invoke(getItem());
            query = query.setParameter(businessKey.getName(), value);
        }
    }
}
