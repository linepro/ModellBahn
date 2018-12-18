package com.linepro.modellbahn.model.keys;

import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.util.Selector;
import com.linepro.modellbahn.util.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Query;
import java.util.Collection;

public class ItemKey extends BaseKey {

    private final IItem<?> item;

    private final Collection<Selector> businessKeys;
    
    public ItemKey(final IItem<?> item, final Collection<Selector> businessKeys) {
        this.item = item;
        this.businessKeys = businessKeys;
    }

    private IItem<?> getItem() {
        return item;
    }

    private Collection<Selector> getBusinessKeys() {
        return businessKeys;
    }

    @Override
    public void addCriteria(Query query) throws Exception {
        for (Selector businessKey : getBusinessKeys()) {
            Object value = businessKey.getGetter().invoke(getItem());
            query = query.setParameter(businessKey.getName(), value);
        }
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(item, ToStringStyle.SHORT_PREFIX_STYLE);

        for (Selector businessKey : getBusinessKeys()) {
            Object value = null;
            try {
                value = businessKey.getGetter().invoke(getItem());
            } catch (Exception e) {
            }
            builder.append(businessKey.getName(), value);
        }

        return builder.toString();
    }
}
